package spring.library.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookLoanRequest {
    private String loanDate;
    private String dueDate;
    private Boolean extendable;
    private int renewalCount;
    private boolean isReturned;
}
