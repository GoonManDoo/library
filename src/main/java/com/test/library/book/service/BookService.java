package com.test.library.book.service;

import com.test.library.book.dto.BookBuilder;
import com.test.library.book.dto.BookDTO;
import com.test.library.book.entity.BookEntity;
import com.test.library.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final을 가진 생성자 자동 생성
public class BookService {

    private final BookRepository bookRepository;
    /* 전체 조회, 단건 조회 = Entity -> DTO 변환
     *  등록, 수정 = DTO -> Entity 변환
     *  클라이언트 -> DB인지, DB -> 클라이언트인지 생각하면 됨 */

    /* 전체 조회 */
    public List<BookEntity> getAllBooks() {
        // Repository에 find.All()을 사용하여 모든 책 정보를 DB에서 가져온다.
        return bookRepository.findAll(); // findAll() == 전체 조회
    }

    /* 단건 조회 */
    public BookDTO getBookById(Long bookUid) {
        // bookUid에 맞는 정보를 가져온다, 해당되는 정보가 없으면 예외 발생
        BookEntity entity = bookRepository.findById(bookUid)
            .orElseThrow(() -> new EntityNotFoundException("고유번호가 없습니다 ! : " + bookUid));
        return BookDTO.toDTO(entity);

    }

    /* 등록 */
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity saveBookEntity = bookRepository.save(BookBuilder.entity(bookDTO));
        return BookDTO.toDTO(saveBookEntity);
    }

    /* 수정 */
    public BookDTO updateBook(Long bookUid, BookDTO bookDTO) {

        /* 책 고유번호로 책을 조회한다, 해당 코드가 없으면 예외 발생 */
        BookEntity existingEntity = bookRepository.findById(bookUid)
                .orElseThrow(() -> new EntityNotFoundException("고유번호가 없습니다 ! : " + bookUid));

        // DTO를 Entity로 변환
        BookEntity updateEntity = BookBuilder.entity(bookDTO);

        // Entity 식별자를 설정
        updateEntity.setBookUid(existingEntity.getBookUid());

        // 수정된 Entity를 DB에 저장하고, updateEntity에 다시 할당
        updateEntity = bookRepository.save(updateEntity);

        return BookDTO.toDTO(updateEntity); // Entity -> DTO 변환하여 반환
    }

    /* 삭제 */
    public void deleteBook(Long bookUid) {
        // 책 고유번호로 책을 조회한다. 해당 코드가 없으면 예외 발생
        BookEntity bookEntity = bookRepository.findById(bookUid)
                .orElseThrow(() -> new EntityNotFoundException("고유번호가 없습니다 ! : " + bookUid));

        // 책 삭제.
        bookRepository.delete(bookEntity);
    }

    /* 책 대출 상태 업데이트 */
    public void updateBookState(Long bookUid, boolean bookState) {
        // 책 고유번호로 책 조회
        BookEntity bookEntity = bookRepository.findById(bookUid).orElse(null);

        if (bookEntity != null) {
            // 책 대출 상태 업데이트
            bookEntity.setBookState(bookState);
            // 업데이트된 책 저장
            bookRepository.save(bookEntity);
        }
    }

    /* 대출 가능한 도서를 가져옴 */
    public BookDTO getAvailableBook() {
        // 대출 가능한 도서 조회
        List<BookEntity> availableBooks = bookRepository.findByBookState(false);

        if (!availableBooks.isEmpty()) {
            // 대출 가능한 도서 중 첫번째 도서를 가져옴
            BookEntity availableBookEntity = availableBooks.get(0);
            // 도서 상태를 true로 변경
            availableBookEntity.setBookState(true);

            availableBookEntity = bookRepository.save(availableBookEntity);

            // Entity를 DTO로 변환
            return BookDTO.toDTO(availableBookEntity);
        }
        // 대출 가능한 도서가 없을 경우 null 반환
        return null;
    }

}
