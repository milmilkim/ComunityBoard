package Gachon.ComunityBoard.controller.dto;

import Gachon.ComunityBoard.domain.posts.Posts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 게시물 저장시 게시물저장api에 전달되는 DTO입니다
 * */

@ApiModel(value = "",description = "")
@Getter
@NoArgsConstructor
public class PostsSaveRequestDTO { // 저장요청할때 쓰는 DTO

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
    private int needPeopleNum;
    @ApiModelProperty(value = "운동위치 x좌표")
    private String locationX;
    @ApiModelProperty(value = "운동위치 y좌표")
    private String locationY;
    @ApiModelProperty(value = "운동위치 주소명", example = "경기도 성남시 수정구~~~")
    private String addressName;
    private String region1Depth;
    private String region2Depth;
    @ApiModelProperty(value = "운동위치 장소명",example = "가천대 운동장")
    private String placeName;
    @ApiModelProperty(value = "운동시간")
    private String EventTime;



    @Builder
    public PostsSaveRequestDTO(String title,String content, String event, int needPeopleNumber, String locationX ,String locationY,
                               String addressName,String region1Depth,String region2Depth,String placeName ,String EventTime){
        this.title = title;
        this.content = content;
        this.event = event;
        this.needPeopleNum = needPeopleNumber;
        this.locationX = locationX;
        this.locationY = locationY;
        this.addressName = addressName;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
        this.placeName = placeName;
        this.EventTime = EventTime;
    }

    public void setWriterAndEamil(String writer,String email) {
        this.writer = writer;
        this.email = email;
    }

    public Posts toEntity(){ // Posts객체를 이 DTO안의 내용을 바탕으로 만들어서 반환
        return Posts.builder()
                .title(title).writer(writer).email(email).content(content).event(event)
                .needPeopleNumber(needPeopleNum).location_x(locationX)
                .location_y(locationY).addressName(addressName).region1Depth(region1Depth)
                .region2Depth(region2Depth).placeName(placeName).eventTime(EventTime)
                .build();
    }

}
