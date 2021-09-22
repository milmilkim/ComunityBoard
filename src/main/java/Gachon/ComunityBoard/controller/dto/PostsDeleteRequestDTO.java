package Gachon.ComunityBoard.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostsDeleteRequestDTO {

    private boolean deleteYN;

    public PostsDeleteRequestDTO(){
        this.deleteYN = true;
    }

    public boolean getDelteYN(){
        return deleteYN;
    }
}
