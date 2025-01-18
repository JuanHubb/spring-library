package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import request.BookRequest;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private int amount;

    public Book update(BookRequest bookRequest) {
        title = bookRequest.getTitle();
        author = bookRequest.getAuthor();
        publisher = bookRequest.getPublisher();
        publicationYear = bookRequest.getPublicationYear();
        classification = bookRequest.getClassification();
        status = bookRequest.getStatus();
        amount = bookRequest.getAmount();
        return this;
    }

    public static Book convertToBook(BookRequest bookRequest){
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .amount(bookRequest.getAmount())
                .build();

    }
}

