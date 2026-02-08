package spring.ai.example.book_review_rag.infrastructure;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentLoader implements CommandLineRunner {
    private final VectorStore vectorStore;

    public DocumentLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Document> documents = new ArrayList<>();

        documents.add(new Document("Friends Marilyn Hacker and Karthika Nair began a correspondence in verse during " +
                "France’s COVID lockdown in March 2020. Renga is an ancient Japanese form of collaborative poetry, " +
                "and the two Parisian poets, create a delightful harmony.",
                Map.of(
                        "bookTitle","A Different Distance",
                        "author","Marilyn Hacker and Karthika Nair"
                )));
        documents.add(new Document(
                "The author whose works frequently focused on diaspora lives tells a moving saga of love and limitations in this novel, " +
                        "part of the Women Translating Women joint series by Zubaan and Ashoka Centre for Translation.",
                Map.of(
                        "bookTitle", "A New World Romance",
                        "author", "Susham Bedi",
                        "translator", "Astri Ghosh"
                )
        ));
        documents.add(new Document(
                "Freelance journalist and screenwriter Vasudha Sahgal’s storytelling began at the age of eight. Her debut solo short fiction " +
                        "delves into the imperfections that make life real, with themes of love and loss, relationships and daily fears.",
                Map.of(
                        "bookTitle", "Almost Perfect, But Mostly Not",
                        "author", "Vasudha Sahgal"
                )
        ));
        documents.add(new Document(
                "This Independence-era novella about jallikattu (bull taming), set to be made into a film with Tamil star Suriya, " +
                        "gets a fresh literary lease of life with International Booker-nominated Perumal Murugan’s script and illustrator Appupen’s sketches.",
                Map.of(
                        "bookTitle", "Vaadivaasal: The Arena",
                        "author", "C.S. Chellappa, Perumal Murugan and Appupen"
                )
        ));
        documents.add(new Document(
                "Stories inspired by the Indian Army continue to feed the imagination of writers and fascinate readers alike. " +
                        "This thriller penned by a retired Army Major draws on real stories from the battlefield and is set in Kashmir.",
                Map.of(
                        "bookTitle", "Kupwara Codes",
                        "author", "Maj. Manik M. Jolly (retd.)"
                )
        ));
        documents.add(new Document(
                "After his debut novel on growing up in a politically charged contemporary India, author Bhattacharya returns with the story " +
                        "of three generations of gay men. It’s important to put yourself out there, he says, talking about the need to increase " +
                        "visibility of the queer community in India.",
                Map.of(
                        "bookTitle", "Deviants",
                        "author", "Santanu Bhattacharya"
                )
        ));
        documents.add(new Document(
                "Her works are often called “extreme” and “bizarre”, but the South Korean Nobel laureate author says her books, " +
                        "based on Korea’s history and society, are an attempt to bridge the gap between human savagery and dignity.",
                Map.of(
                        "bookTitle", "We Do Not Part",
                        "author", "Han Kang",
                        "translator", "e. yaewon and Paige Aniyah Morris"
                )
        ));
        documents.add(new Document(
                "Through exercises, drawings, and questionnaires, this crime thriller by the Kerala Sahitya Akademi award-winning author delves into India’s underground sex toy trade, via stories narrated by characters who are long dead.",
                Map.of(
                        "bookTitle", "A Pulp Fiction Textbook",
                        "author", "V.M. Devadas"
                )
        ));
        documents.add(new Document(
                "The Pakistani author’s keen understanding of people, and knack for political commentary come to the fore yet again " +
                        "in this story set in 16th century Mughal India. It takes its name from the unlikely female protagonist who goes on a mission " +
                        "for the Emperor with three strange men.",
                Map.of(
                        "bookTitle", "Ferdowsnama",
                        "author", "Shandana Minhas"
                )
        ));
        documents.add(new Document(
                "This is a collection of essays in which Murugan delves into the lives of college students in small-town Tamil Nadu " +
                        "and follows their journeys. The essays reflect the challenges they face in family and society.",
                Map.of(
                        "bookTitle", "Students Etched in Memory",
                        "author", "Perumal Murugan",
                        "translator", "Iswarya V."
                )
        ));
        documents.add(new Document(
                "Focusing on the activities of a group of ‘Radicals’ called ‘Young Bengal’, students of Henry Derozio, Chaudhuri examines their achievements in the 19th century. They campaigned for the rights of peasants, and fought against corruption, and racial, gender and caste discrimination.",
                Map.of(
                        "bookTitle", "India’s First Radicals",
                        "author", "Rosinka Chaudhuri"
                )
        ));
        documents.add(new Document(
                "This journalist with the New York Times, who grew up in Washington DC as the son of Chinese immigrants with family secrets, " +
                        "returned to Beijing and probed his father’s mysterious past. In his memoir, he tells the story of China, its past and present, " +
                        "through the story of his family.",
                Map.of(
                        "bookTitle", "At the Edge of Empire",
                        "author", "Edward Wong"
                )
        ));
        documents.add(new Document(
                "Questioning the violence in West Asia, Mishra reckons with several fundamental questions posed by the crisis in Gaza — " +
                        "how some lives are valued more than others, why the West supports Israel, and why racist, far-right movements are surging " +
                        "in many countries.",
                Map.of(
                        "bookTitle", "The World After Gaza",
                        "author", "Pankaj Mishra"
                )
        ));
        documents.add(new Document(
                "Another book of poetry set against the pandemic but in Sri Lanka. The academic who has written widely on gender equality " +
                        "explores universal topics such as war and feminism as well as her privileged status as the First Lady of Sri Lanka.",
                Map.of(
                        "bookTitle", "Unexpectedly",
                        "author", "Maithree Wickramasinghe"
                )
        ));
    }
}
