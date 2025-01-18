package service;

import domain.Book;
import dto.BookDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import request.BookRequest;

import java.util.List;

@Service
@Getter
@Setter
public class BookService {
    private BookRepository bookRepository;

    public BookDto createBook(BookRequest bookRequest){
        return BookDto.convertToBookDto(bookRepository.save(Book.convertToBook(bookRequest)));

    }

    public BookDto getBookById(Long id){
        return BookDto.convertToBookDto(bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")));
    }

    public List<BookDto> getAllBooks(){
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
