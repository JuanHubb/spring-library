package spring.library.controller;

import spring.library.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.BookRequest;
import spring.library.controller.response.BookResponse;
import spring.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    public final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest){
        BookDto bookDto = bookService.createBook(bookRequest);
        return ResponseEntity.ok(BookResponse.convertToBookResponse(bookDto));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId){
        BookDto bookDto = bookService.getBookById(bookId);
        return ResponseEntity.ok(BookResponse.convertToBookResponse(bookDto));
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookResponse>> getAllBook(){
        List<BookResponse> book = bookService.getAllBook().stream().map(BookResponse::convertToBookResponse).toList();
        return ResponseEntity.ok(book);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody BookRequest bookRequest){
        BookResponse bookResponse = BookResponse.convertToBookResponse(bookService.updateBookById(bookId, bookRequest));
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }
}
