package spring.library.service;

import spring.library.controller.request.BookLoanRequest;
import spring.library.domain.Book;
import spring.library.domain.BookLoan;
import spring.library.domain.Member;
import spring.library.dto.BookLoanDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import spring.library.repository.BookLoanRepository;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class BookLoanService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BookLoanRepository bookLoanRepository;

    public BookLoanDto createBookLoan(BookLoanRequest bookLoanRequest, Long bookId) throws ParseException {
        Member member = memberRepository.findById(bookLoanRequest.getMemberId()).orElseThrow(()-> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        BookLoan bookLoan = BookLoan.convertToBookLoan(member,book);
        return BookLoanDto.convertToBookLoanDto(bookLoanRepository.save(bookLoan));
    }

    public List<BookLoanDto> getCurrentBookLoan() {
        List<BookLoan> targetBooks = bookLoanRepository.findAll();
        List<BookLoan> onLoan = new ArrayList<>();
        for (BookLoan eachBookLoan : targetBooks) {
            if (!eachBookLoan.getIsReturned()) {
                onLoan.add(eachBookLoan);
            }
        }
        return onLoan.stream().map(BookLoanDto::convertToBookLoanDto).toList();
    }

    public List<BookLoanDto> getBookLoanHistory(){
        return bookLoanRepository.findAll().stream().map(BookLoanDto::convertToBookLoanDto).toList();
    }

    @Transactional
    public BookLoanDto returnABook(Long bookLoanId){
        BookLoan targetBook = bookLoanRepository.findById(bookLoanId).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        return BookLoanDto.convertToBookLoanDto(targetBook.update());
    }
}


