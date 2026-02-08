package spring.ai.example.book_review_rag.ingestion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileScanner {
    public List<Path> listHtmlFiles(Path dir) throws IOException {
        if(!Files.exists(dir)) {
            throw new IOException("Directory does not exist: " + dir);
        }
        try(Stream<Path> stream = Files.walk(dir)) {
            return stream.filter(Files::isRegularFile)
                    .filter(p->p.getFileName().toString().endsWith("html"))
                    .collect(Collectors.toList());
        }

    }
}
