package spring.ai.example.book_review_rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.ai.example.book_review_rag.service.router.RetrievalDecision;
import spring.ai.example.book_review_rag.service.router.RetrievalDecisionService;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class PhiChatService implements RetrievalDecisionService {
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("classpath:/prompts/phi3-prompt.st")
    private Resource phi3Prompt;

    public PhiChatService(@Qualifier("phiChatClient")ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public RetrievalDecision decide(String userMessage) {
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(phi3Prompt);
        Prompt prompt = new Prompt(
                List.of(
                        systemPromptTemplate.createMessage(
                                Map.of("user_message", userMessage)
                        )
                )
        );
        String response = chatClient.prompt(prompt).call().content();

        try{
            return objectMapper.readValue(response,RetrievalDecision.class);
        }catch(Exception e){
            return new RetrievalDecision(true,userMessage);

        }
    }


}
