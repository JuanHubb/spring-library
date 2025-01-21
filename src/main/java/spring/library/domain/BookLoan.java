package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.libraryClock.DateCounter;

import java.text.ParseException;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ManagingId;
    private String loanDate;
    private String dueDate;
    private int renewalCount;
    private Boolean isReturned;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    public BookLoan update() {
        isReturned = true;
        return this;
    }

    public static BookLoan convertToBookLoan(Member member, Book book) throws ParseException {
        return BookLoan.builder()
                .member(member)
                .book(book)
                .loanDate(DateCounter.today())
                .dueDate(DateCounter.setDueDateByFeature(member.getFeature()))
                .renewalCount(1)
                .isReturned(false)
                .build();
    }
}

//
//