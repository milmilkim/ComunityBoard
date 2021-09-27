package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDTO {
    //게시판 처음부분에 들어가면
    // 리스트처럼 보여줄것을 불러오는 DTO
    // 그 조회화면에 보여질것만 담아옴
    // 혹은 검색시 나올 리스트.

    private Long id;
    private String title;
    private String writer;
    private String event;

    public PostsListResponseDTO(Posts post){
        this.id = post.getIdx();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.event = post.getEvent();
    }



}
