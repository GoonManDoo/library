package com.test.library.rent.dto;

import com.test.library.book.dto.BookDTO;
import com.test.library.book.entity.BookEntity;
import com.test.library.rent.entity.RentEntity;
import org.springframework.stereotype.Component;

@Component
public class RentBuilder {

    public static RentEntity entity(RentDTO rentDTO) { // DTO -> Entity 변환
        return RentEntity.builder() // builder을 이용하여 entity 객체 생성
                .rentUid(rentDTO.getRentUid())
                .rentDate(rentDTO.getRentDate())
                .returnDate(rentDTO.getReturnDate())
                .bookUid(rentDTO.getBookUid()) // 수정된 부분
                .usersUid(rentDTO.getUsersUid())
                .build();
    }



}
