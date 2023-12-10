package com.test.library.record.repository;

import com.test.library.record.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    /* 대출 이력 조회 */
    // 대출 고유번호로 대출 이력 조회
    List<RecordEntity> findByRentUid(Long rentUid);
}
