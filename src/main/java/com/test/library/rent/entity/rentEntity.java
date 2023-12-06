package com.test.library.rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rent")
public class rentEntity {

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

    /* 대출 상태 */
    @Column(length = 1, columnDefinition = "varchar (1) default 'N'")
    private String rent_state;

}
