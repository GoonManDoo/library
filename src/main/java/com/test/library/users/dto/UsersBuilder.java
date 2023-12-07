package com.test.library.users.dto;


import com.test.library.users.entity.UsersEntity;

public class UsersBuilder {

    public static UsersEntity entity(UsersDTO usersDTO) { // DTO -> Entity 변환
        return UsersEntity.builder() // builder을 이용하여 entity 객체 생성
                .usersUid(usersDTO.getUsersUid())
                .usersName(usersDTO.getUsersName())
                .usersId(usersDTO.getUsersId())
                .usersPw(usersDTO.getUsersPw())
                .usersState(usersDTO.getUsersState())
                .build();
    }

}
