package Gachon.ComunityBoard.controller.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 정보 수정시 유저정보수정 api에 전달되는 DTO입니다
 * */


@ApiModel(value = "",description = "")
@Getter
@NoArgsConstructor
public class UserUpdateRequestDTO {
    // 유저정보 수정할때 수정할수있는거만 포함
    @ApiModelProperty(value = "유저 닉네임")
    private String nickname;
    @ApiModelProperty(value = "자기소개")
    private String selfIntroduction;
    @ApiModelProperty(value = "사는곳")
    private String livingPlace;

    // 선호 운동 1 2 3
    @ApiModelProperty(value = "선호운동1")
    private String preference1;
    @ApiModelProperty(value = "선호운동2")
    private String preference2;
    @ApiModelProperty(value = "선호운동3")
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
