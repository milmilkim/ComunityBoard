package Gachon.ComunityBoard.service.user;


import Gachon.ComunityBoard.controller.dto.PostsResponseDTO;
import Gachon.ComunityBoard.controller.dto.UserResponseDTO;
import Gachon.ComunityBoard.controller.dto.UserUpdateRequestDTO;
import Gachon.ComunityBoard.domain.user.User;
import Gachon.ComunityBoard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 이메일로 게시글 조회
    public UserResponseDTO findByEamil(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email = "+email));
        return new UserResponseDTO(user);
    }

    //유저 정보 수정
    @Transactional
    public String update(String email, UserUpdateRequestDTO updateDTO){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email = "+email));
        user.updateSelfIntroduction(updateDTO.getNickname(),updateDTO.getSelfIntroduction());
        return email;
    }






}
