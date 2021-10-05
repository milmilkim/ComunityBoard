package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LoginedUserResponseDTO {

    @ApiModelProperty(value = "유저 이메일", example = "hello@gachon.ac.kr")
    private String email;


    public LoginedUserResponseDTO(User user){
        this.email = user.getEmail();
    }
}
