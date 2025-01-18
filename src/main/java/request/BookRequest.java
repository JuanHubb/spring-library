package request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private int amount;
}
