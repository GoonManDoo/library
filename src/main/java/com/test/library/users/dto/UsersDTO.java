package com.test.library.users.dto;

import com.test.library.users.entity.UsersEntity;
import lombok.Data;

@Data
public class UsersDTO {

    private Long usersUid; // 사용자 고유번호
    private String usersName; // 사용자 이름
    private String usersId; // 사용자 아이디
    private String usersPw; // 사용자 암호

    public UsersDTO() {

    }

    // 생성자
    public UsersDTO(Long usersUid, String usersName, String usersId, String usersPw) {
        this.usersUid = usersUid;
        this.usersName = usersName;
        this.usersId = usersId;
        this.usersPw = usersPw;
    }

    /* ex) DB에서 조회 할 때 정보를 Entity 객체에 담아오고, DTO로 변환하여 클라이언트에 전달 */
    public static UsersDTO toDTO(UsersEntity entity) { // Entity -> DTO 변환
        UsersDTO dto = new UsersDTO(); // DTO 객체 생성
        dto.setUsersUid(entity.getUsersUid());
        dto.setUsersName(entity.getUsersName());
        dto.setUsersId(entity.getUsersId());
        dto.setUsersPw(entity.getUsersPw());
        return dto;
    }

}
