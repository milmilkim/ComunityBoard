package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDTO {// 게시물 조회할때 쓰는 DTO

    private Long idx;
    private String title;
    private String writer;
    private String content;
    private String event;
    private int NeedPeopleNumber;
    private boolean IsRecruiting;
    private int location;
    private LocalDateTime EventTime;


    public PostsResponseDTO(Posts posts){
        this.idx = posts.getIdx();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.content = posts.getContent();
        this.event = posts.getEvent();
        this.NeedPeopleNumber = posts.getNeedPeopleNumber();
        this.IsRecruiting = posts.isIsRecruiting();
        this.location = posts.getLocation();
//        this.EventTime = posts.getEventTime();
    }


}
