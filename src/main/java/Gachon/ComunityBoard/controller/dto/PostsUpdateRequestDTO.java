package Gachon.ComunityBoard.controller.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 게시글 수정시 게시물 수정api에 전달되는 DTO입니다
 * */

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDTO { // Update요청할때 쓰는 DTO
    // 등록때는 작성자도 쓰지만 수정때는 작성자는 수정대상이 아니다.
    // 값이 들어왔는지 아닌지를 알기위해 먼저 초기화를 시켜놓음
    @ApiModelProperty(value = "제목")
    private String title;
    @ApiModelProperty(value = "게시글 내용")
    private String content;
    @ApiModelProperty(value = "운동 종목")
    private String event;
    @ApiModelProperty(value = "필요인원")
    private int needPeopleNum;
    @ApiModelProperty(value = "운동위치 x좌표")
    private String locationX;
    @ApiModelProperty(value = "운동위치 y좌표")
    private String locationY;
    @ApiModelProperty(value = "운동위치 주소명")
    private String addressName;
    private String region1Depth;
    private String region2Depth;
    @ApiModelProperty(value = "변경될 운동장소명")
    private String placeName;
    @ApiModelProperty(value = "변경 운동시간")
    private String eventTime;

    @Builder
    public PostsUpdateRequestDTO(String title,String content, String event, int needPeopleNum, String locationX,String locationY,String addressName,String region1Depth,String region2Depth,String placeName, String eventTime){
        this.title = title;
        this.content = content;
        this.event = event;
        this.needPeopleNum = needPeopleNum;
        this.locationX = locationX;
        this.locationY = locationY;
        this.addressName = addressName;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
        this.placeName = placeName;
        this.eventTime = eventTime;

    }

}
