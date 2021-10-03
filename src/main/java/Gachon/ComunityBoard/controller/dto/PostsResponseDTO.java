package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDTO {// 게시물 조회할때 쓰는 DTO

    private Long idx;
    private String title;
    private String writer;
    private String email;
    private String content;
    private String event;
    private int NeedPeopleNumber;
    private boolean IsRecruiting;
    private String locationName;
    private LocalDateTime EventTime;
    private boolean isMine;

    public void setMine(boolean isMine){
        this.isMine = isMine;
    }


    public PostsResponseDTO(Posts posts){
        this.idx = posts.getIdx();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.email = posts.getEmail();

        this.content = posts.getContent();
        this.event = posts.getEvent();
        this.NeedPeopleNumber = posts.getNeedPeopleNumber();
        this.IsRecruiting = posts.isIsRecruiting();
        this.locationName = posts.getLocationName();
//        this.EventTime = posts.getEventTime();
    }


}
