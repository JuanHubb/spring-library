package spring.library.controller;

import spring.library.dto.BookLoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.response.BookLoanResponse;
import spring.library.service.BookLoanService;

@RestController
@RequiredArgsConstructor
public class BookLoanController {
    public final BookLoanService bookLoanService;

    @PostMapping("/bookLoans/{bookLoanId}/checkout")
    public ResponseEntity<BookLoanResponse> addBookLoan(@PathVariable Long bookId) {
        BookLoanDto bookLoanDto = bookLoanService.createBookLoan(bookId);
        return ResponseEntity.ok(BookLoanResponse.convertToBookLoanResponse(bookLoanDto));
    }

    @GetMapping("/bookLoans/checkout")
    public ResponseEntity<BookLoanResponse> getBookLoan(@PathVariable Long bookLoanId) {
        BookLoanDto bookLoanDto = bookLoanService.getBookLoanById(bookLoanId);
        return ResponseEntity.ok(BookLoanResponse.convertToBookLoanResponse(bookLoanDto));
    }

//    @GetMapping("/bookLoans/history")
//    public ResponseEntity<List<BookLoanResponse>> getAllBookLoan(){
//        List<BookLoanResponse> bookLoan = bookLoanService.getAllBookLoan().stream().map(BookLoanResponse::convertToBookLoanResponse).toList();
//        return ResponseEntity.ok(bookLoan);
//    }
//
//    @PutMapping("/bookLoans/{bookLoanId}/return")
//    public ResponseEntity<BookLoanResponse> updateBookLoan(@PathVariable Long bookLoanId, @RequestBody BookLoanRequest bookLoanRequest){
//        BookLoanResponse bookLoanResponse = BookLoanResponse.convertToBookLoanResponse(bookLoanService.updateBookLoanById(bookLoanId, bookLoanRequest));
//        return ResponseEntity.ok(bookLoanResponse);
//    }
//
//    @PutMapping("/bookLoans/{bookLoanId}/renewal")
//    public ResponseEntity<BookLoanResponse> updateBookLoan(@PathVariable Long bookLoanId, @RequestBody BookLoanRequest bookLoanRequest){
//        BookLoanResponse bookLoanResponse = BookLoanResponse.convertToBookLoanResponse(bookLoanService.updateBookLoanById(bookLoanId, bookLoanRequest));
//        return ResponseEntity.ok(bookLoanResponse);
//    }
}


