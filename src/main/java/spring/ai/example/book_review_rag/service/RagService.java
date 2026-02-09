package spring.ai.example.book_review_rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.ai.example.book_review_rag.service.router.RetrievalDecision;
import spring.ai.example.book_review_rag.service.router.RetrievalDecisionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RagService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final RetrievalDecisionService retrievalDecisionService;

    @Value("classpath:/prompts/rag-prompt.st")
    private Resource RagTemplate;


    public RagService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore,
                      ChatMemory chatMemory, RetrievalDecisionService retrievalDecisionService) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
        this.vectorStore = vectorStore;
        this.retrievalDecisionService = retrievalDecisionService;
    }

    public String retrieveAndGenerate(String message) {
        RetrievalDecision decision = retrievalDecisionService.decide(message);
        String information = "";
        String retrievalStatus;
        if(!decision.retrieve()){
            retrievalStatus = "SKIPPED";
        }else {
            List<Document> similarDocs = vectorStore
                    .similaritySearch(SearchRequest
                            .builder()
                            .query(decision.query())
                            .topK(5)
                            .build());
            System.out.println("similarDocs: " + similarDocs);
            if(similarDocs.isEmpty()){
                retrievalStatus = "EMPTY";
            }else{
                retrievalStatus = "FOUND";

                information = similarDocs
                    .stream()
                    .map(doc -> {
                        String meta = "Title: " + doc.getMetadata().get("title")
                                + ", Author: " + doc.getMetadata().get("author");
                        if (doc.getMetadata().containsKey("translator")) {
                            meta += ", Translator: " + doc.getMetadata().get("translator");
                        }
                        return meta + "\n" + doc.getText();
                    })
                    .collect(Collectors.joining("\n\n"));
            }
        }
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(RagTemplate);

        Prompt prompt = new Prompt(List.of(
                systemPromptTemplate.createMessage(
                        Map.of("information", information,
                                "retrieval_status",retrievalStatus)),
                new UserMessage(message)
                )
        );
//        System.out.println("prompt: " + prompt.getContents());

        return chatClient.prompt(prompt).call().content();

    }

}
