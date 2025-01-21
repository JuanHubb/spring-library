package spring.library.dto;

import spring.library.domain.BookLoan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookLoanDto {
    private Long bookId;
    private String loanDate;
    private String dueDate;
    private int renewalCount;
    private Boolean isReturned;
    private MemberDto member;
    private BookDto book;

    public static BookLoanDto convertToBookLoanDto(BookLoan bookLoan) {
        return BookLoanDto.builder()
                .bookId(bookLoan.getBook().getBookId())
                .book(BookDto.convertToBookDto(bookLoan.getBook()))
                .loanDate(bookLoan.getLoanDate())
                .dueDate(bookLoan.getDueDate())
                .renewalCount(bookLoan.getRenewalCount())
                .isReturned(bookLoan.getIsReturned())
                .build();
    }
}
