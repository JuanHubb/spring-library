package spring.library.service;

import spring.library.common.MyException;
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
import static spring.library.common.DateCounter.today;

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

        if(!isLoanAvailable(determineLoanLimit(member.getFeature()))){
            throw new MyException("대출 가능한 권수가 없습니다");
        }
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
        if (targetBook.getIsReturned()){
            throw new MyException("이미 반납이 완료된 도서입니다.");
        }
        return BookLoanDto.convertToBookLoanDto(targetBook.update());
    }

    @Transactional
    public BookLoanDto extendLoan(Long bookLoanId){
        BookLoan targetBook = bookLoanRepository.findById(bookLoanId).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        if (!targetBook.getDueDate().equals(today())) {
            throw new MyException("반납일에만 기간 연장이 가능합니다.");
        }
        return BookLoanDto.convertToBookLoanDto(targetBook.extendLoanDate());
    }

    public int determineLoanLimit(String feature){
        return switch (feature) {
            case "관리자" -> 10;
            case "교직원" -> 20;
            case "학생" -> 100;
            default -> 0;
        };
    }

    public Boolean isLoanAvailable(int totalAmount) {
        List<BookLoan> targetBooks = bookLoanRepository.findAll();
        int booksOnLoanCount = 0;

        for (BookLoan eachBookLoan : targetBooks) {
            if (!eachBookLoan.getIsReturned()) {
                booksOnLoanCount++;
            }
        }
        return booksOnLoanCount < totalAmount;
    }
}


