package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private String nickname;
    private String email;
    private String picture;
    private String selfIntroduction;

    public UserResponseDTO(User user){
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.selfIntroduction = user.getSelfIntroduction();
    }
}
