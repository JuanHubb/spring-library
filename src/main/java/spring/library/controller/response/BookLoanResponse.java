package spring.library.controller.response;

import lombok.*;
import spring.library.dto.BookLoanDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLoanResponse {
    private String loanDate;
    private String dueDate;
    private Boolean extendable;
    private int renewalCount;
    private boolean isReturned;

    public static BookLoanResponse convertToBookLoanResponse(BookLoanDto bookLoanDto){
        return BookLoanResponse.builder()
                .loanDate(bookLoanDto.getLoanDate())
                .dueDate(bookLoanDto.getDueDate())
                .extendable(bookLoanDto.getExtendable())
                .renewalCount(bookLoanDto.getRenewalCount())
                .isReturned(bookLoanDto.isReturned())
                .build();
    }
}
