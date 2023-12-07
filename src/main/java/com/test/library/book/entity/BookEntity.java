package com.test.library.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "book")
public class BookEntity {

    /* 책 고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookUid;

    /* 책 이름 */
    @Column(length = 40, nullable = false)
    private String bookName;

    /* 책 저자 */
    @Column(length = 40, nullable = false)
    private String bookWriter;

    /* 대출 상태 */
    @Column(length = 1, columnDefinition = "varchar (1) default 'N'")
    private String bookState;

    /* 기본 생성자 추가 */
    public BookEntity() {
    }

    /* 초기화용 생성자 */
    public BookEntity(Long bookUid) {
        this.bookUid = bookUid;
    }

    @Builder
    public BookEntity(Long bookUid, String bookName, String bookWriter, String bookState) {
        this.bookUid = bookUid;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookState = bookState;
    }

}
