package Gachon.ComunityBoard.controller.dto;


import Gachon.ComunityBoard.domain.posts.Posts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 게시글 조회시 프론트로 전달되는 DTO입니다
 * */
@ApiModel(value = "",description = "")
@Getter
public class PostsResponseDTO {// 게시물 조회할때 쓰는 DTO

    private Long idx;
    @ApiModelProperty(value = "제목",example = "축구할사람 2명 구해요!")
    private String title;
    @ApiModelProperty(value = "작성자")
    private String writer;
    @ApiModelProperty(value = "작성자email")
    private String email;
    @ApiModelProperty(value = "게시글 내용",example = "가천대 운동장에서 축구할 사람 2명 모십니다")
    private String content;
    @ApiModelProperty(value = "운동 종목",example = "야구")
    private String event;
    @ApiModelProperty(value = "필요인원")
    private int NeedPeopleNumber;
    @ApiModelProperty(value = "모집중 여부")
    private boolean isRecruiting;
    @ApiModelProperty(value = "운동위치 주소명", example = "경기도 성남시 수정구~~~")
    private String addressName;
    @ApiModelProperty(value = "운동위치 x좌표")
    private String locationX;
    @ApiModelProperty(value = "운동위치 y좌표")
    private String locationY;
    @ApiModelProperty(value = "운동위치 장소명",example = "가천대 운동장")
    private String placeName;
    @ApiModelProperty(value = "운동시간")
    private String EventTime;
    @ApiModelProperty(value = "내가쓴글 여부")
    private boolean isMine;

    public void setMine(boolean isMine){
        this.isMine = isMine;
    }


    public PostsResponseDTO(Posts posts){
        this.idx = posts.getIdx();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.email = posts.getEmail();
        this.locationX = posts.getLocationX();
        this.locationY = posts.getLocationY();
        this.content = posts.getContent();
        this.event = posts.getEvent();
        this.NeedPeopleNumber = posts.getNeedPeopleNumber();
        this.isRecruiting = posts.isRecruiting();
        this.addressName = posts.getAddressName();
        this.placeName = posts.getPlaceName();
        this.EventTime = posts.getEventTime();
    }


}
