package Gachon.ComunityBoard.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDTO {
    // 유저정보 수정할때 수정할수있는거만 포함
    private String nickname;
    private String selfIntroduction;
    //지역
    private String livingPlace;

    // 선호 운동 1 2 3
    private String preference1;
    private String preference2;
    private String preference3;

    @Builder
    public UserUpdateRequestDTO(String nickname,String livingPlace
            ,String preference1, String preference2, String preference3,String selfIntroduction){
        this.nickname=nickname;
        this.livingPlace = livingPlace;
        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
        this.selfIntroduction = selfIntroduction;
    }


}
