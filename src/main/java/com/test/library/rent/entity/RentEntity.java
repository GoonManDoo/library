package com.test.library.rent.entity;

import com.test.library.book.entity.BookEntity;
import com.test.library.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "rent")
public class RentEntity {

    /* 대출 고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentUid;

    /* 대출일 */
    @CreationTimestamp
    @Column(updatable = false)
    private String rentDate;

    /* 반납일 */
    @CreationTimestamp
    @Column(updatable = false)
    private String returnDate;

    /* 책 고유번호 */
    @OneToOne
    @JoinColumn(name = "book_uid", referencedColumnName = "bookUid")
    private BookEntity book;

    /* 사용자 고유번호 */
    @ManyToOne
    @JoinColumn(name = "users_uid", referencedColumnName = "usersUid")
    private UsersEntity user;


}
