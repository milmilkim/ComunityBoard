package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private String nickname;
    private String email;
    private String picture;
    private String selfIntroduction;
    private String livingPlace;
    private String preference1;
    private String preference2;
    private String preference3;

    private boolean mine;

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public UserResponseDTO(User user){
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.selfIntroduction = user.getSelfIntroduction();
        this.livingPlace = user.getLivingPlace();
        this.preference1 = user.getPreference1();
        this.preference2 = user.getPreference2();
        this.preference3 = user.getPreference3();
    }
}
