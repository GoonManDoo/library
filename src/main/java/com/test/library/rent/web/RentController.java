package com.test.library.rent.web;

import com.test.library.rent.dto.RentDTO;
import com.test.library.rent.entity.RentEntity;
import com.test.library.rent.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "대출")
public class RentController {

    private final RentService rentService;

    /* 대출 */
    @PostMapping("/rent/{userId}")
    @Operation(summary = "책 대출", description = "책 대출")
    public ResponseEntity<RentDTO> rentBook(@PathVariable Long userId) {
        // 대출된 정보를 반환
        RentDTO rentBook = RentDTO.toDTO(rentService.rentBook(userId));
        if (rentBook != null) {
            return new ResponseEntity<>(rentBook, HttpStatus.CREATED);
        } else {
            // 실패 시 BAD_REQUEST 반환
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /* 반납 */
    @PostMapping("/return/{rentUid}")
    @Operation(summary = "책 반납", description = "책 반납")
    public ResponseEntity<Void> returnBook(@PathVariable Long rentUid) {
        rentService.returnBook(rentUid);
        // 성공 시 OK 반환
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
