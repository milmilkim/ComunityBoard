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

    @GetMapping("/api/user/userInfo/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email){
        return userService.findByEamil(email);
    }

    // 유저 정보 수정
    @PutMapping("/api/user/userInfo/{email}")
    public String update(@PathVariable String email, @RequestBody UserUpdateRequestDTO updateDTO){
        return userService.update(email, updateDTO);
    }



}
