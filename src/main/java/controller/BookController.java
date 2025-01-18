package controller;

import dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.BookRequest;
import response.BookResponse;
import service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    public final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest){
        BookDto bookDto = bookService.createBook(bookRequest);
        return ResponseEntity.ok(BookResponse.convertToBookResponse(bookDto));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId){
        BookDto bookDto = bookService.getBookById(bookId);
        return ResponseEntity.ok(BookResponse.convertToBookResponse(bookDto));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        List<BookResponse> books = bookService.getAllBooks().stream().map(BookResponse::convertToBookResponse).toList();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody BookRequest bookRequest){
        BookResponse bookResponse = BookResponse.convertToBookResponse(bookService.updateBookById(bookId, bookRequest));
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }
}
