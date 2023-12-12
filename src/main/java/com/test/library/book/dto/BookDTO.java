package com.test.library.book.dto;

import com.test.library.book.entity.BookEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Schema(description = "책 정보 DTO")
public class BookDTO {

    @Schema(description = "책 고유번호", example = "1")
    private Long bookUid; // 책 고유번호

    @Schema(description = "책 이름", example = "난중일기")
    private String bookName; // 책 이름

    @Schema(description = "책 저자", example = "이순신")
    private String bookWriter; // 책 저자

    @Schema(description = "책 고유번호", example = "false")
    private boolean bookState; // 대출 상태

    public BookDTO() {

    }

    // 생성자
    public BookDTO(Long bookUid, String bookName, String bookWriter, boolean bookState) {
        this.bookUid = bookUid;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookState = bookState;
    }

    /* ex) DB에서 조회 할 때 정보를 Entity 객체에 담아오고, DTO로 변환하여 클라이언트에 전달 */
    public static BookDTO toDTO(BookEntity entity) { // Entity -> DTO 변환
        BookDTO dto = new BookDTO(); // DTO 객체 생성
        dto.setBookUid(entity.getBookUid());
        dto.setBookName(entity.getBookName());
        dto.setBookWriter(entity.getBookWriter());
        //dto.setBookState(entity.getBookState());
        return dto;
    }

    // 대출 가능 여부를 판단하는 메소드

}
