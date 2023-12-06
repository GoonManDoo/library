package com.test.library.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class bookEntity {

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

}
