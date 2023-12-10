package com.test.library.record.dto;

import com.test.library.record.entity.RecordEntity;
import com.test.library.rent.dto.RentDTO;
import com.test.library.rent.entity.RentEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Builder
public class RecordDTO {

    private Long recordUid; // 이력 고유번호
    private Date recordDate; // 이력 시간
    private Long rentUid; // 대출 고유번호
    private String recordText; // 이력 내용

    public RecordDTO() {

    }

    // 생성자
    public RecordDTO(Long recordUid, Date recordDate, Long rentUid, String recordText) {
        this.recordUid = recordUid;
        this.recordDate = recordDate;
        this.rentUid = rentUid;
        this.recordText = recordText;
    }

    /* ex) DB에서 조회 할 때 정보를 Entity 객체에 담아오고, DTO로 변환하여 클라이언트에 전달 */
    public static RecordDTO toDTO(RecordEntity entity) {
        return RecordDTO.builder()
                .recordUid(entity.getRecordUid())
                .recordDate(entity.getRecordDate())
                .rentUid(entity.getRentUid())
                .recordText(entity.getRecordText())
                .build();
    }

}
