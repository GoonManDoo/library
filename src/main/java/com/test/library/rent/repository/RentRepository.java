package com.test.library.rent.repository;

import com.test.library.book.entity.BookEntity;
import com.test.library.rent.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

}
