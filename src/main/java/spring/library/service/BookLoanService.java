package spring.library.service;

import spring.library.domain.Book;
import spring.library.domain.BookLoan;
import spring.library.dto.BookLoanDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import spring.library.repository.BookRepository;
import spring.library.controller.request.BookLoanRequest;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class BookLoanService {
    private final BookRepository bookRepository;

    public BookLoanDto createBookLoan(Long bookId){
        Book book= bookRepository.findById(bookId).orElseThrow(()-> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        return BookLoanDto.convertToBookLoanDto(bookRepository.save(BookLoan.convertToBookLoan()));

    }

    public BookLoanDto getBookLoanById(Long id){
        return BookLoanDto.convertToBookLoanDto(bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")));
    }

    public List<BookLoanDto> getAllBookLoan(){
        return bookRepository.findAll().stream().map(BookLoanDto::convertToBookLoanDto).toList();
    }

    @Transactional
    public BookLoanDto updateBookLoanById(Long id, BookLoanRequest bookLoanRequest){
        BookLoan bookLoan = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return BookLoanDto.convertToBookLoanDto(bookLoan.update(bookLoanRequest));
    }
}

