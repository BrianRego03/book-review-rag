package spring.ai.example.book_review_rag.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import spring.ai.example.book_review_rag.dto.ParsedReview;

import java.util.HashMap;
import java.util.Map;

@Service
public class MiniReviewParseService {
    public ParsedReview parse(String html) {
        Document doc = Jsoup.parse(html);

        Map<String, Object> metadata = new HashMap<>();


        Element titleEl = doc.selectFirst("div.header p");
        if (titleEl != null) {
            metadata.put("title", titleEl.text().trim());
        }


        Element authorEl = doc.selectFirst("span.bold");
        if (authorEl != null) {
            parseAuthorAndTranslator(authorEl.text(), metadata);
        }


        String reviewText = extractReviewText(doc);

        return new ParsedReview(reviewText, metadata);
    }

    private void parseAuthorAndTranslator(String raw, Map<String, Object> metadata) {
        // Examples:
        // "Rosinka Chaudhuri"
        // "Perumal Murugan, translated by Iswarya V."
        // "Perumal Murugan, translated by Iswarya V"

        String lower = raw.toLowerCase();

        if (lower.contains("translated by")) {
            String[] parts = raw.split(",(?i)\\s*translated by\\s*");
            metadata.put("author", parts[0].trim());
            if (parts.length > 1) {
                metadata.put("translator", parts[1].replaceAll("\\.$", "").trim());
            }
        } else {
            metadata.put("author", raw.trim());
        }
    }

    private String extractReviewText(Document doc) {
        Elements paragraphs = doc.select("div.body.taggroup-body > p");

        if (paragraphs == null || paragraphs.isEmpty()) {
            return "";
        }

        Element last = paragraphs.last();
        if (last == null) {
            return "";
        }

        return last.text().trim();
    }
}
