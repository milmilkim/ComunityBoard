package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.posts.Posts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 게시판 리스트로 쭈욱 나열할때 전달되는DTO입니다
 * */

@ApiModel(value = "",description = "")
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
    private String userPicture;
    private String locationX;
    private String locationY;
    private String region1Depth;
    private String region2Depth;
    private String placeName;
    private int needPeopleNum;
    private String eventTime;
    private boolean isRecruiting; // 이거로 모집중여부 판단

    public PostsListResponseDTO(Posts post){
        this.id = post.getIdx();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.userPicture = post.getUserPicture();
        this.event = post.getEvent();
        this.locationX = post.getLocationX();
        this.locationY = post.getLocationY();
        this.region1Depth = post.getRegion1Depth();
        this.region2Depth = post.getRegion2Depth();
        this.placeName = post.getPlaceName();
        this.needPeopleNum = post.getNeedPeopleNum();
        this.eventTime = post.getEventTime();
        this.isRecruiting = post.isRecruiting();
    }



}
