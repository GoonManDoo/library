package com.test.library.users.service;

import com.test.library.users.dto.UsersBuilder;
import com.test.library.users.dto.UsersDTO;
import com.test.library.users.entity.UsersEntity;
import com.test.library.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final을 가진 생성자 자동 생성
public class UsersService {

    private final UsersRepository usersRepository;
    /* 전체 조회, 단건 조회 = Entity -> DTO 변환
     *  등록, 수정 = DTO -> Entity 변환
     *  클라이언트 -> DB인지, DB -> 클라이언트인지 생각하면 됨 */

    /* 전체 조회 */
    public List<UsersEntity> getAllUsers() {
        // Repository에 find.All()을 사용하여 모든 사용자 정보를 DB에서 가져온다.
        return usersRepository.findAll(); // findAll() == 전체 조회
    }

    /* 단건 조회 */
    public UsersDTO getUsersById(Long usersUid) {
        // usersUid에 맞는 정보를 가져온다, 해당되는 정보가 없으면 예외 발생
        UsersEntity entity = usersRepository.findById(usersUid)
            .orElseThrow(() -> new EntityNotFoundException("고유번호가 없습니다 ! : " + usersUid));
        return UsersDTO.toDTO(entity);

    }

    /* 등록 */
    public UsersDTO createUsers(UsersDTO usersDTO) {
        UsersEntity saveUsersEntity = usersRepository.save(UsersBuilder.entity(usersDTO));
        return UsersDTO.toDTO(saveUsersEntity);
    }

    /* 수정 */
    public UsersDTO updateUsers(Long usersUid, UsersDTO usersDTO) {

        /* 사용자 고유번호로 사용자를 조회한다, 해당 코드가 없으면 예외 발생 */
        UsersEntity existingEntity = usersRepository.findById(usersUid)
                .orElseThrow(() -> new EntityNotFoundException("고유번호가 없습니다 ! : " + usersUid));

        // DTO를 Entity로 변환
        UsersEntity updateEntity = UsersBuilder.entity(usersDTO);

        // Entity 식별자를 설정
        updateEntity.setUsersUid(existingEntity.getUsersUid());

        // 수정된 Entity를 DB에 저장하고, updateEntity에 다시 할당
        updateEntity = usersRepository.save(updateEntity);

        return UsersDTO.toDTO(updateEntity); // Entity -> DTO 변환하여 반환
    }

    /* 삭제 */
    public void deleteUsers(Long usersUid) {
        // 사용자 고유번호로 사용자을 조회한다. 해당 코드가 없으면 예외 발생
        UsersEntity usersEntity = usersRepository.findById(usersUid)
                .orElseThrow(() -> new EntityNotFoundException("고유번호가 없습니다 ! : " + usersUid));

        // 사용자 삭제
        usersRepository.delete(usersEntity);
    }

}
