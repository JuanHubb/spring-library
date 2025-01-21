package spring.library.controller.response;

import lombok.*;
import spring.library.dto.BookLoanDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLoanHistoryResponse {
    private Long bookId;
    private String title;
    private String author;
    private String loanDate;
    private String dueDate;
    private int renewalCount;
    private Boolean isReturned;

    public static BookLoanHistoryResponse convertToBookLoanHistoryResponse(BookLoanDto bookLoanDto){
        return BookLoanHistoryResponse.builder()
                .bookId(bookLoanDto.getBookId())
                .title(bookLoanDto.getBook().getTitle())
                .author(bookLoanDto.getBook().getAuthor())
                .loanDate(bookLoanDto.getLoanDate())
                .dueDate(bookLoanDto.getDueDate())
                .renewalCount(bookLoanDto.getRenewalCount())
                .isReturned(bookLoanDto.getIsReturned())
                .build();
    }
}
