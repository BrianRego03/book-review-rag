package spring.ai.example.book_review_rag.dto;

import java.util.Map;

public class ParsedReview {
    private final String text;
    private final Map<String,String> metadata;
    public ParsedReview(String text, Map<String,String> metadata) {
        this.text = text;
        this.metadata = metadata;
    }

    public String getText() {
        return text;
    }
    public Map<String, String> getMetadata() {
        return metadata;
    }

}
