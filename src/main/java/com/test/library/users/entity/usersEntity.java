package com.test.library.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class usersEntity {

    /* 사용자 고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersUid;

    /* 사용자 이름 */
    @Column(length = 40, nullable = false)
    private String users_name;

    /* 사용자 아이디 */
    @Column(length = 40, nullable = false)
    private String users_id;

    /* 사용자 암호 */
    @Column(length = 40, nullable = false)
    private String users_pw;

    /* 대출현황 */
    @Column(nullable = false, columnDefinition = "int default 'N'")
    private int users_state;

}
