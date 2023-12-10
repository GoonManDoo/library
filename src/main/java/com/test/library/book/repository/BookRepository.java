package com.test.library.book.repository;

import com.test.library.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /* 대출 가능한 도서 조회 */
    // bookState가 true인 책 반환
    List<BookEntity> findByBookState(boolean bookState);
}
