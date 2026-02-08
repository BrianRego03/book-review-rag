package spring.ai.example.book_review_rag.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.example.book_review_rag.service.RagService;

@RestController
public class RagController {
    private final RagService ragService;
    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ai/rag")
    public String generate(@RequestBody MessageRequest messageRequest){
        return ragService.retrieveAndGenerate(messageRequest.message());

    }

    public static record MessageRequest(String message){}

}
