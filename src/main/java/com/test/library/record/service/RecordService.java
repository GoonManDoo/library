package com.test.library.record.service;

import com.test.library.record.dto.RecordDTO;
import com.test.library.record.entity.RecordEntity;
import com.test.library.record.repository.RecordRepository;
import com.test.library.rent.entity.RentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    /* 전체 조회 */
    public List<RecordDTO> getAllRecords() {
        // 모든 대출 이력을 가져온다
        List<RecordEntity> recordEntities = recordRepository.findAll();
        return recordEntities
                .stream() // List -> stream()으로 변환
                .map(RecordDTO::toDTO) // 각각의 Entity를 DTO로 변환
                .collect(Collectors.toList()); // stream() -> List 변환
    }

    /* 단건 조회 */
    public RecordDTO getRecordById(Long recordUid) {
        RecordEntity recordEntity = recordRepository.findById(recordUid)
                .orElse(null);
        return RecordDTO.toDTO(recordEntity);
    }

    /* 대출 고유번호로 이력 조회 */
    public List<RecordDTO> getRecordsByRentUid(Long rentUid) {
        List<RecordEntity> recordEntities = recordRepository.findByRentUid(rentUid);
        return recordEntities
                .stream() // List -> stream()으로 변환
                .map(RecordDTO::toDTO) // 각각의 Entity를 DTO로 변환
                .collect(Collectors.toList()); // stream() -> List 변환
    }

    /* 이력 생성 */
    public RecordDTO createRecord(RecordDTO recordDTO) {
        RecordEntity recordEntity = RecordEntity.builder()
                .recordDate(recordDTO.getRecordDate())
                .rentUid(recordDTO.getRentUid())
                .recordText(recordDTO.getRecordText())
                .build();

        RecordEntity savedRecordEntity = recordRepository.save(recordEntity);
        return RecordDTO.toDTO(savedRecordEntity);
    }

}
