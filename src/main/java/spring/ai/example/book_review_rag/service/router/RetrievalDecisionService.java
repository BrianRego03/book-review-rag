package spring.ai.example.book_review_rag.service.router;

public interface RetrievalDecisionService {
    RetrievalDecision decide(String userMessage);
}
