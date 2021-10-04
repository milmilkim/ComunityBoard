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
    private boolean isRecruiting;
    private String addressName;
//    private String region1Depth;
//    private String region2Depth;
    private String placeName;
    private String EventTime;
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
        this.isRecruiting = posts.isRecruiting();
        this.addressName = posts.getAddressName();
        this.placeName = posts.getPlaceName();
        this.EventTime = posts.getEventTime();
    }


}
