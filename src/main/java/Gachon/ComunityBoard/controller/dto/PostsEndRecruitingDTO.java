package Gachon.ComunityBoard.controller.dto;


import io.swagger.annotations.ApiModel;
import lombok.Getter;


/**
 * 모집 종료 요청api에 전달되는 DTO입니다
 * */
@ApiModel(value = "",description = "")
@Getter
public class PostsEndRecruitingDTO {

    private boolean isRecruiting;

    public PostsEndRecruitingDTO(){
        this.isRecruiting = false;
    }

    public boolean getIsRecruiting() {
        return isRecruiting;
    }
}
