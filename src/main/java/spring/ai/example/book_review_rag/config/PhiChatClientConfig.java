package spring.ai.example.book_review_rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhiChatClientConfig {
    @Bean
    public ChatClient phiChatClient(OllamaChatModel phiChatModel) {
        return ChatClient
                .builder(phiChatModel)
                .build();

    }
}
