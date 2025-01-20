package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.BookLoanRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Boolean extendable;
    private int renewalCount;
    private boolean isReturned;


    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


//    public static String currentTime() {
//        Date now = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.format(now);
//    }


    public BookLoan update(BookLoanRequest bookLoanRequest) {
        return this;
    }

    public static BookLoan convertToBookLoan(Member member, Book book){
        return BookLoan.builder()
                .member(member)
                .book(book)
                .loanDate()
                .extendable()
                .renewalCount(1)
                .isReturned(false)
                .build();
    }
}
