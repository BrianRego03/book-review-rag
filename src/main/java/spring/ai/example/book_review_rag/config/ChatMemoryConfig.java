package spring.ai.example.book_review_rag.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;

public class ChatMemoryConfig {

    @Bean
    public ChatMemory chatMemory(){
        return MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
    }
}
