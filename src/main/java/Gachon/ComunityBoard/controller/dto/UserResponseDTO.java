package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 유저정보 조회시 프론트로 넘어가는 DTO값 입니다.
 * */

@ApiModel(value = "",description = "")
@Getter
public class UserResponseDTO {

    @ApiModelProperty(value = "유저 닉네임",example = "가천만세")
    private String nickname;
    @ApiModelProperty(value = "유저 이메일", example = "hello@gachon.ac.kr")
    private String email;
    @ApiModelProperty(value = "유저 사진")
    private String picture;
    @ApiModelProperty(value = "자기소개",example = "안녕하세요 반갑습니다!")
    private String selfIntroduction;
    @ApiModelProperty(value = "사는곳",example = "성남시 수정구")
    private String livingPlace;
    @ApiModelProperty(value = "선호운동1",example = "야구")
    private String preference1;
    @ApiModelProperty(value = "선호운동2",example = "축구")
    private String preference2;
    @ApiModelProperty(value = "선호운동3",example = "농구")
    private String preference3;

    @ApiModelProperty(value = "내 user page인가 확인")
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
