package spring.ai.example.book_review_rag.service.router;

public record RetrievalDecision(boolean retrieve, String query) {}
