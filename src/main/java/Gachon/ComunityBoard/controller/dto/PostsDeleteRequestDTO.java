package Gachon.ComunityBoard.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
