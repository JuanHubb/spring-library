package spring.library.controller;

import spring.library.controller.request.BookLoanRequest;
import spring.library.dto.BookLoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.response.BookLoanResponse;
import spring.library.service.BookLoanService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookLoanController {
    public final BookLoanService bookLoanService;

    @PostMapping("/books/{bookId}/checkout")
    public ResponseEntity<BookLoanResponse> addBookLoan(@RequestBody BookLoanRequest bookLoanRequest, @PathVariable Long bookId) throws ParseException {
        BookLoanDto bookLoanDto = bookLoanService.createBookLoan(bookLoanRequest, bookId);
        return ResponseEntity.ok(BookLoanResponse.convertToBookLoanResponse(bookLoanDto));
    }

    @GetMapping("/books/checkout")
    public ResponseEntity<List<BookLoanResponse>> getCurrentBookLoan() {
        List<BookLoanResponse> bookLoans = bookLoanService.getCurrentBookLoan().stream().map(BookLoanResponse::convertToBookLoanResponse).toList();
        return ResponseEntity.ok(bookLoans);
    }

    @GetMapping("/books/history")
    public ResponseEntity<List<BookLoanResponse>> getBookLoanHistory(){
        List<BookLoanResponse> bookLoans = bookLoanService.getBookLoanHistory().stream().map(BookLoanResponse::convertToBookLoanResponse).toList();
        return ResponseEntity.ok(bookLoans);
    }

    @PutMapping("/books/{bookId}/return")
    public ResponseEntity<BookLoanResponse> returnOneBook(@PathVariable Long bookId){
        BookLoanResponse bookLoanResponse = BookLoanResponse.convertToBookLoanResponse(bookLoanService.returnABook(bookId));
        return ResponseEntity.ok(bookLoanResponse);
    }

    @PutMapping("/books/{bookLoanId}/renewal")
    public ResponseEntity<BookLoanResponse> extendLoan(@PathVariable Long bookLoanId){
        BookLoanResponse bookLoanResponse = BookLoanResponse.convertToBookLoanResponse(bookLoanService.extendLoan(bookLoanId));
        return ResponseEntity.ok(bookLoanResponse);
    }
}


