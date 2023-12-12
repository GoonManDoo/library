package com.test.library.users.dto;

import com.test.library.users.entity.UsersEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사용자 정보 DTO")
public class UsersDTO {

    @Schema(description = "사용자 고유번호", example = "1")
    private Long usersUid; // 사용자 고유번호
    
    @Schema(description = "사용자 이름", example = "김영민")
    private String usersName; // 사용자 이름

    @Schema(description = "사용자 아이디", example = "id")
    private String usersId; // 사용자 아이디

    @Schema(description = "사용자 암호", example = "pw")
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
