package com.test.library.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class UsersEntity {

    /* 사용자 고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersUid;

    /* 사용자 이름 */
    @Column(length = 40, nullable = false)
    private String usersName;

    /* 사용자 아이디 */
    @Column(length = 40, nullable = false)
    private String usersId;

    /* 사용자 암호 */
    @Column(length = 40, nullable = false)
    private String usersPw;

    /* 대출현황 */
    @Column(nullable = false, columnDefinition = "int default 0")
    private int usersState;

    /* 기본 생성자 추가 */
    public UsersEntity() {
    }

    /* 초기화용 생성자 */
    public UsersEntity(Long usersUid) {
        this.usersUid = usersUid;
    }

    @Builder
    public UsersEntity(Long usersUid, String usersName, String usersId, String usersPw, int usersState) {
        this.usersUid = usersUid;
        this.usersName = usersName;
        this.usersId = usersId;
        this.usersPw = usersPw;
        this.usersState = usersState;
    }

}
