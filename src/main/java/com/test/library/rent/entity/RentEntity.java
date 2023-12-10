package com.test.library.rent.entity;

import com.test.library.book.entity.BookEntity;
import com.test.library.record.entity.RecordEntity;
import com.test.library.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date rentDate;

    /* 반납일 */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date returnDate;

    /* 책 고유번호 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_uid", referencedColumnName = "bookUid")
    private BookEntity book;

    /* 사용자 고유번호 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_uid", referencedColumnName = "usersUid")
    private UsersEntity user;

    @Transient
    private RecordEntity recordEntity;

    /* 기본 생성자 추가 */
    public RentEntity() {
    }

    /* 초기화용 생성자 */
    public RentEntity(Long rentUid) {
        this.rentUid = rentUid;
    }

    /* 대출일을 등록하는 생성자 */
    public RentEntity(BookEntity book, UsersEntity user) {
        this.book = book;
        this.user = user;
        this.rentDate = new Date(); // 현재 날짜로 대출일 설정
    }

    @Builder
    public RentEntity(Long rentUid, Date rentDate, Date returnDate, Long bookUid, Long usersUid) {
        this.rentUid = rentUid;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.book = BookEntity.builder().bookUid(bookUid).build();
        this.user = UsersEntity.builder().usersUid(usersUid).build();
    }

    @Builder
    public RentEntity(Long rentUid, Date rentDate, Date returnDate, BookEntity book, UsersEntity user) {
        this.rentUid = rentUid;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }


}
