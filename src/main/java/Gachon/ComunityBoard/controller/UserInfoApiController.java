package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.config.auth.LoginUser;
import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.controller.dto.UserResponseDTO;
import Gachon.ComunityBoard.controller.dto.UserUpdateRequestDTO;
import Gachon.ComunityBoard.domain.user.UserRepository;
import Gachon.ComunityBoard.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserInfoApiController {
    private final UserService userService;
    private final UserRepository userRepository;

    // 유저정보 조회

    @ApiOperation(value = "유저 조회",notes = "email에 해당하는 유저를 조회합니다")
    @GetMapping("/api/user/userInfo/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email){
        // 조회대상 사람
        UserResponseDTO userResponseDTO = userService.findByEamil(email);

        // 조회하려는사람과 조회대상이 같다면(내 프로필을 보려한다면)
//        if(user.getEmail().equals(userResponseDTO.getEmail())){
//            userResponseDTO.setMine(true); // 수정버튼 보이게
//        }else{
//            userResponseDTO.setMine(false); // 수정버튼 안보이게
//        }
        return userResponseDTO;
    }


    @ApiOperation(value = "유저 조회",notes = "id에 해당하는 유저를 조회합니다")
    @GetMapping("/api/user/userInfo/id/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        // 조회대상 사람
        UserResponseDTO userResponseDTO = userService.findById(id);

        return userResponseDTO;
    }

    // 유저 정보 수정
    @ApiOperation(value = "사용자 정보 수정",notes = "사용자 정보를 수정합니다.")
    @PutMapping("/api/user/userInfo/{email}")
    public String update(@PathVariable String email, @RequestBody UserUpdateRequestDTO updateDTO){
        //중복된닉네임이면 예외발생 이부분 지금 문제 셤끝나고 해결
//        if(userService.nicknameCheck(updateDTO.getNickname())){
//            return userService.update(email, updateDTO);
//        }else {
//            return "닉네임이 중복된 값입니다.";
//        }
        return userService.update(email, updateDTO);
    }

    // 닉네임수정 중복확인 (유저정보 수정시 사용)

    @ApiOperation(value = "닉네임변경 중복체크",notes = "닉네임을 변경할때 중복인지를 검사합니다")
    @GetMapping("/api/user/userInfo/DuplicateCheck/{nickname}")
    public boolean nicknameDuplicateCheck(@PathVariable String nickname){
        // false 리턴되면 중복된것. true가 리턴되면 중복안된것
        return userService.nicknameCheck(nickname);
    }

//    @GetMapping("/api/loginedUser")
//    public String LoginedUserEmail(@LoginUser SessionUser user){
//        if(user != null){
//            return user.getEmail();
//        }else{
//            return "";
//        }
//    }
//
//    @GetMapping("/api/loginedUser")
//    public String LoginedUserEmail(){
//
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if(user != null){
//            return user.getEmail();
//        }else{
//            return "";
//        }
//    }



}
