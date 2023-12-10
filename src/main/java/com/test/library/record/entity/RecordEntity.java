package com.test.library.record.entity;

import com.test.library.book.entity.BookEntity;
import com.test.library.rent.entity.RentEntity;
import com.test.library.users.entity.UsersEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder // Lombok의 @Builder 애노테이션 추가
@NoArgsConstructor // Lombok의 기본 생성자 생성
@AllArgsConstructor // Lombok의 모든 인자를 받는 생성자 생성
@Table(name = "record")
@Tag(name = "대출 이력")
public class RecordEntity {

    /* 이력 고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_uid")
    private Long recordUid;

    /* 이력 시간 */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_date", nullable = false)
    private Date recordDate;

    /* 대출 고유번호 */
    @Column(name = "rent_uid", nullable = false)
    private Long rentUid;

    /* 이력 내용 */
    @Column(name = "record_text", nullable = false)
    private String recordText;

    /* 대출 고유번호 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_uid", nullable = false, insertable = false, updatable = false)
    private RentEntity rentEntity;


}
