package com.test.library.users.web;

import com.test.library.users.dto.UsersDTO;
import com.test.library.users.entity.UsersEntity;
import com.test.library.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor // final을 가진 생성자 자동 생성
@Tag(name = "Users", description = "사용자 정보 API")
public class UsersController {

    private final UsersService usersService; // 생성자 주입 방식

    /* 전체 조회 */
    @GetMapping("/selectUsersList")
    @Operation(summary = "사용자 전체 조회", description = "등록된 사용자를 전체 조회한다.")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {

        // Service에서 모든 사용자 정보를 조회하여 List<UsersEntity>로 받아옴
        List<UsersEntity> usersEntityList = usersService.getAllUsers();

        List<UsersDTO> usersDTOList = usersEntityList
                .stream() // List -> stream()으로 변환
                .map(UsersDTO::toDTO) // 각각의 Entity를 DTO로 변환
                .collect(Collectors.toList()); // stream() -> List 변환

        return new ResponseEntity<>(usersDTOList, HttpStatus.OK);
    }

    /* 단건 조회 */
    @GetMapping("/selectUsers/{usersUid}")
    @Operation(summary = "사용자 단건 조회", description = "사용자 고유번호를 통해 사용자를 조회한다.")
    public ResponseEntity<UsersDTO> getUsersById(@Parameter(name = "usersUid", description = "사용자의 uid", in = ParameterIn.PATH,
                                                            example = "1", required = true)
                                                 @PathVariable Long usersUid) {
        // Service에 getUsersById(Long usersUid)를 호출하여 단일 정보를 가져오고 DTO에 저장.
        // Service에서 Controller로 넘겨줄 때 Entity -> DTO로 변환하여 넘겨줌
        UsersDTO usersDTO = usersService.getUsersById(usersUid);

        if (usersDTO == null) { // UsersDTO가 null이면 해당 404 에러 코드 반환
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // 성공 시 200 코드 반환
            return new ResponseEntity<>(usersDTO, HttpStatus.OK);
        }
    }

    /* 등록 */
    @PostMapping("/insertUsers")
    @Operation(summary = "사용자 등록", description = "사용자를 등록한다.")
    public ResponseEntity<UsersDTO> createUsers(@RequestBody UsersDTO usersDTO) {
        /* usersService.createUsers(usersDTO)을 호출하여
		   등록 정보가 담긴 DTO를 사용하여 사용자를 등록하고, 등록된 정보를 createUsers 저장 */
        UsersDTO createUsers = usersService.createUsers(usersDTO);

        // 사용자 정보를 ResponseEntity로 감싸서 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createUsers);
    }

    /* 수정 */
    @PutMapping("/updateUsers/{usersUid}")
    @Operation(summary = "사용자 수정", description = "등록된 사용자를 수정한다.")
    public ResponseEntity<UsersDTO> updateUsers(@Parameter(name = "usersUid", description = "사용자의 uid", in = ParameterIn.PATH,
                                                           example = "1", required = true)
                                                @PathVariable Long usersUid, @RequestBody UsersDTO usersDTO) {

		/* UsersService.updateUsers(UsersUid, usersDTO)을 호출하여
		   등록 정보가 담긴 DTO를 사용하여 사용자을 등록하고, 등록된 정보를 updatedUsers에 저장 */
        UsersDTO updateUsers = usersService.updateUsers(usersUid, usersDTO);

        // 사용자 정보를 ResponseEntity로 감싸서 반환
        return new ResponseEntity<>(updateUsers, HttpStatus.OK);
    }

    /* 삭제 */
    @DeleteMapping("/deleteUsers/{usersUid}")
    @Operation(summary = "사용자 삭제", description = "등록된 사용자를 삭제한다.")
    public ResponseEntity<Void> deleteUsers(@Parameter(name = "usersUid", description = "사용자의 uid", in = ParameterIn.PATH,
                                                       example = "1", required = true)
                                            @PathVariable Long usersUid) {
        usersService.deleteUsers(usersUid);
        return ResponseEntity.noContent().build();
    }


}
