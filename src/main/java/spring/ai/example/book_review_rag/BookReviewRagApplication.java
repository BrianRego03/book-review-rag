package spring.ai.example.book_review_rag;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookReviewRagApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
		System.setProperty("OLLAMA_URL", dotenv.get("OLLAMA_URL"));
		System.setProperty("EMBEDDING_MODEL", dotenv.get("EMBEDDING_MODEL"));
		System.setProperty("CHAT_MODEL", dotenv.get("CHAT_MODEL"));
		System.setProperty("TABLE_NAME", dotenv.get("TABLE_NAME"));
		System.setProperty("FILE_PATH", dotenv.get("FILE_PATH"));
		SpringApplication.run(BookReviewRagApplication.class, args);
	}

}
