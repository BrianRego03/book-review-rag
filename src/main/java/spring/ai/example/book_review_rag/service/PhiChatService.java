package spring.ai.example.book_review_rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PhiChatService {
    private final ChatClient chatClient;

    public PhiChatService(@Qualifier("phiChatClient")ChatClient chatClient) {
        this.chatClient = chatClient;
    }


}
