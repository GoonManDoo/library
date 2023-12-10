package com.test.library.rent.service;

import com.test.library.book.dto.BookDTO;
import com.test.library.book.entity.BookEntity;
import com.test.library.book.repository.BookRepository;
import com.test.library.book.service.BookService;
import com.test.library.record.entity.RecordEntity;
import com.test.library.record.repository.RecordRepository;
import com.test.library.record.service.RecordService;
import com.test.library.rent.dto.RentBuilder;
import com.test.library.rent.dto.RentDTO;
import com.test.library.rent.entity.RentEntity;
import com.test.library.rent.repository.RentRepository;
import com.test.library.users.dto.UsersDTO;
import com.test.library.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final BookService bookService;
    private final RecordService recordService;
    private final UsersService usersService;
    private final RentBuilder rentBuilder;
    private final RecordRepository recordRepository;

    /* 대출 */
    public RentEntity rentBook(Long userId) {
        // 사용자 조회
        UsersDTO user = usersService.getUsersById(userId);

        // 사용자가 있으면 대출 가능한 책 조회
        if (user != null) {
            BookDTO availableBook = bookService.getAvailableBook();

            if (availableBook != null) {
                // 책 상태를 true로 변경
                availableBook.setBookState(true);

                // 대출 정보 DTO 생성
                RentDTO rentDTO = RentDTO.builder()
                        .bookUid(availableBook.getBookUid())
                        .usersUid(userId)
                        .build();

                RentEntity rent = rentBuilder.entity(rentDTO);
                rentRepository.save(rent);

                // 대출 시 RecordEntity 생성(대출 이력)
                createRecord(rent, "대출 이력 - " + availableBook.getBookName());

                // 대출된 책 상태 업데이트
                bookService.updateBookState(availableBook.getBookUid(), true);

                return rent;
            }
        }
        return null;
    }

    /* 반납 */
    public void returnBook(Long rentUid) {
        // 대출 고유번호로 대출 정보 조회
        RentEntity rentEntity = rentRepository.findById(rentUid)
                .orElseThrow(() -> new IllegalArgumentException("대출 고유번호를 찾을 수 없습니다 !: " + rentUid));

        // 책 정보 조회
        BookDTO book = bookService.getBookById(rentEntity.getBook().getBookUid());

        if (book != null) {
            // 책 상태를 false로 변경
            book.setBookState(false);

            // 반납 시 RecordEntity 생성(반납 이력)
            createRecord(rentEntity, "반납 이력 - " + book.getBookName());

            // 반납된 책 상태 업데이트
            bookService.updateBookState(rentEntity.getBook().getBookUid(), false);
            // 반납일을 현재 날짜로 저장
            rentEntity.setReturnDate(new Date());
            rentRepository.save(rentEntity);
        }
    }

    // 대출 이력 RecordEntity 생성
    private void createRecord(RentEntity rentEntity, String recordText) {
        RecordEntity recordEntity = RecordEntity.builder()
                .rentUid(rentEntity.getRentUid())
                .recordText(recordText)
                .build();

        recordRepository.save(recordEntity);
    }

}
