package com.test.library.book.dto;

import com.test.library.book.entity.BookEntity;

public class BookBuilder {

    public static BookEntity entity(BookDTO bookDTO) { // DTO -> Entity 변환
        return BookEntity.builder() // builder을 이용하여 entity 객체 생성
                .bookUid(bookDTO.getBookUid())
                .bookName(bookDTO.getBookName())
                .bookWriter(bookDTO.getBookWriter())
                //.bookState(bookDTO.getBookState())
                .build();
    }

}
