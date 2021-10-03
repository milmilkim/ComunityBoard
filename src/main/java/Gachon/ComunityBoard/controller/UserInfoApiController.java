package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.UserResponseDTO;
import Gachon.ComunityBoard.controller.dto.UserUpdateRequestDTO;
import Gachon.ComunityBoard.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserInfoApiController {
    private final UserService userService;

    // 유저정보 조회
    @GetMapping("/api/user/userInfo/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email){
        return userService.findByEamil(email);
    }

    // 유저 정보 수정
    @PutMapping("/api/user/userInfo/{email}")
    public String update(@PathVariable String email, @RequestBody UserUpdateRequestDTO updateDTO){
        //중복된닉네임이면 예외발생
        if(userService.nicknameCheck(updateDTO.getNickname())){
            return userService.update(email, updateDTO);
        }else {
            return "닉네임이 중복된 값입니다.";
        }
    }

    // 닉네임수정 중복확인
    @GetMapping("/api/user/userInfo/DuplicateCheck/{nickname}")
    public boolean nicknameDuplicateCheck(@PathVariable String nickname){
        // false 리턴되면 중복된것. true가 리턴되면 중복안된것
        return userService.nicknameCheck(nickname);
    }



}
