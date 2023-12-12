package com.test.library.rent.dto;

import com.test.library.book.entity.BookEntity;
import com.test.library.rent.entity.RentEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(description = "대출 정보 DTO")
public class RentDTO {

    @Schema(description = "대출 고유번호", example = "1")
    private Long rentUid;      // 대출 고유번호

    @Schema(description = "대출일")
    private Date rentDate;    // 대출일

    @Schema(description = "반납일")
    private Date returnDate;  // 반납일

    @Schema(description = "책 고유번호", example = "1")
    private Long bookUid;       // 책 고유번호

    @Schema(description = "사용자 고유번호", example = "1", hidden = true)
    private Long usersUid;      // 사용자 고유번호

    public RentDTO() {

    }

    // 생성자
    public RentDTO(Long rentUid, Date rentDate, Date returnDate, Long bookUid, Long usersUid) {
        this.rentUid = rentUid;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.bookUid = bookUid;
        this.usersUid = usersUid;
    }

    /* ex) DB에서 조회 할 때 정보를 Entity 객체에 담아오고, DTO로 변환하여 클라이언트에 전달 */
    public static RentDTO toDTO(RentEntity entity) { // Entity -> DTO 변환
        RentDTO dto = new RentDTO(); // DTO 객체 생성
        dto.setRentUid(entity.getRentUid());
        dto.setRentDate(entity.getRentDate());
        dto.setReturnDate(entity.getReturnDate());
        dto.setBookUid(entity.getBook().getBookUid());
        dto.setUsersUid(entity.getUser().getUsersUid());
        return dto;
    }

}
