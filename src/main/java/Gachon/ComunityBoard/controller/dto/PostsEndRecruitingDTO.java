package Gachon.ComunityBoard.controller.dto;


import lombok.Getter;

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
