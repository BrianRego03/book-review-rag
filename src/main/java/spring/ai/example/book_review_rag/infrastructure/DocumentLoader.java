package spring.ai.example.book_review_rag.infrastructure;

import org.jsoup.Jsoup;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.ai.example.book_review_rag.dto.ParsedReview;
import spring.ai.example.book_review_rag.ingestion.FileScanner;
import spring.ai.example.book_review_rag.ingestion.HtmlReader;
import spring.ai.example.book_review_rag.service.MiniReviewParseService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DocumentLoader implements CommandLineRunner {
    private final VectorStore vectorStore;
    private final MiniReviewParseService miniReviewParseService;


    @Value("${FILE_PATH}")
    private Path folderPath;

    public DocumentLoader(VectorStore vectorStore, MiniReviewParseService miniReviewParseService) {
        this.vectorStore = vectorStore;
        this.miniReviewParseService = miniReviewParseService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Document> documents = new ArrayList<>();

        FileScanner folderScanner = new FileScanner();

        List<Path> fileList = folderScanner.listHtmlFiles(folderPath);

        for (Path path : fileList) {
            String htmlContent = HtmlReader.readHtmlFile(path);
            ParsedReview reviewObj = miniReviewParseService.parse(htmlContent);

            Document doc = new Document(reviewObj.getText(),reviewObj.getMetadata());
            documents.add(doc);
//            System.out.println("---- DOCUMENT ----");
//            System.out.println("TEXT:");
//            System.out.println(doc.getText());
//            System.out.println("METADATA:");
//            System.out.println(doc.getMetadata());
//            System.out.println("------------------");
        }
        vectorStore.add(documents);

//        documents.add(new Document(
//                "Another book of poetry set against the pandemic but in Sri Lanka. The academic who has written widely on gender equality " +
//                        "explores universal topics such as war and feminism as well as her privileged status as the First Lady of Sri Lanka.",
//                Map.of(
//                        "bookTitle", "Unexpectedly",
//                        "author", "Maithree Wickramasinghe"
//                )
//        ));
    }
}
