package spring.library.controller.response;

import lombok.*;
import spring.library.dto.BookLoanDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLoanResponse {
    private Long bookId;
    private String loanDate;
    private String dueDate;
    private int renewalCount;
    private Boolean isReturned;

    public static BookLoanResponse convertToBookLoanResponse(BookLoanDto bookLoanDto){
        return BookLoanResponse.builder()
                .bookId(bookLoanDto.getBookId())
                .loanDate(bookLoanDto.getLoanDate())
                .dueDate(bookLoanDto.getDueDate())
                .renewalCount(bookLoanDto.getRenewalCount())
                .isReturned(bookLoanDto.getIsReturned())
                .build();
    }
}
