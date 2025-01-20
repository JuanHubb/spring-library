package spring.library.service;

import lombok.RequiredArgsConstructor;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import spring.library.repository.BookRepository;
import spring.library.controller.request.BookRequest;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookDto createBook(BookRequest bookRequest){
        return BookDto.convertToBookDto(bookRepository.save(Book.convertToBook(bookRequest)));

    }

    public BookDto getBookById(Long id){
        return BookDto.convertToBookDto(bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")));
    }

    public List<BookDto> getAllBook(){
        return bookRepository.findAll().stream().map(BookDto::convertToBookDto).toList();
    }

    @Transactional
    public BookDto updateBookById(Long id, BookRequest bookRequest){
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return BookDto.convertToBookDto(book.update(bookRequest));
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
