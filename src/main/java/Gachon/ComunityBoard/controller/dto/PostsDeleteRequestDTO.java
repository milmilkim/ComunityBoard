package Gachon.ComunityBoard.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 삭제 요청api에 전달되는 DTO입니다
 * */

@ApiModel(value = "삭제 요청",description = "삭제요청 api입니다")
@Getter
public class PostsDeleteRequestDTO {
    //삭제요청시 쓰이는 DTO
    private boolean deleteYN;

    public PostsDeleteRequestDTO(){
        this.deleteYN = true;
    }

    public boolean getDelteYN(){
        return deleteYN;
    }
}
