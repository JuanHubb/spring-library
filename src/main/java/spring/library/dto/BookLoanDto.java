package spring.library.dto;

import spring.library.domain.BookLoan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookLoanDto {
    private String loanDate;
    private String dueDate;
    private Boolean extendable;
    private int renewalCount;
    private boolean isReturned;

    public static BookLoanDto convertToBookLoanDto(BookLoan bookLoan) {
        return BookLoanDto.builder()
                .loanDate(bookLoan.getLoanDate())
                .dueDate(bookLoan.getDueDate())
                .extendable(bookLoan.getExtendable())
                .renewalCount(bookLoan.getRenewalCount())
                .isReturned(bookLoan.isReturned())
                .build();
    }
}
