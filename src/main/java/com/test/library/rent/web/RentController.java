package com.test.library.rent.web;

import com.test.library.book.dto.BookDTO;
import com.test.library.rent.dto.RentDTO;
import com.test.library.rent.entity.RentEntity;
import com.test.library.rent.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rent", description = "대출 정보 API")
public class RentController {

    private final RentService rentService;

    /* 대출 */
    @PostMapping("/rent/{userId}")
    @Operation(summary = "책 대출", description = "책을 대출한다.")
    public ResponseEntity<RentDTO> rentBook(@Parameter(name = "userId", description = "사용자의 uid", in = ParameterIn.PATH,
                                                       example = "1", required = true)
                                            @PathVariable Long userId,
                                            @RequestBody RentDTO rentDTO) {
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
    @Operation(summary = "책 반납", description = "책을 반납한다.")
    public ResponseEntity<Void> returnBook(@Parameter(name = "rentUid", description = "대출 uid", in = ParameterIn.PATH,
                                                      example = "1", required = true)
                                           @PathVariable Long rentUid,
                                           @RequestBody RentDTO rentDTO) {
        rentService.returnBook(rentUid);
        // 성공 시 OK 반환
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
