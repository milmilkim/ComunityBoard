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

    @Builder
    public UserUpdateRequestDTO(String nickname,String selfIntroduction){
        this.nickname=nickname;
        this.selfIntroduction = selfIntroduction;
    }


}
