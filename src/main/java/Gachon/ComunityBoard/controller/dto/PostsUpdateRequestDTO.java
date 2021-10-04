package Gachon.ComunityBoard.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDTO { // Update요청할때 쓰는 DTO
    // 등록때는 작성자도 쓰지만 수정때는 작성자는 수정대상이 아니다.
    // 값이 들어왔는지 아닌지를 알기위해 먼저 초기화를 시켜놓음
    private String title;
    private String content;
    private String event;
    private int needPeopleNum;
    private String location_x;
    private String location_y;
    private String addressName;
    private String region1Depth;
    private String region2Depth;
    private String placeName;
    private String modifiedEventTime;

    @Builder
    public PostsUpdateRequestDTO(String title,String content, String event, int needPeopleNumber, String location_x,String location_y,String addressName,String region1Depth,String region2Depth,String placeName, String modifiedEventTime){
        this.title = title;
        this.content = content;
        this.event = event;
        this.needPeopleNum = needPeopleNumber;
        this.location_x = location_x;
        this.location_y = location_y;
        this.addressName = addressName;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
        this.placeName = placeName;
        this.modifiedEventTime = modifiedEventTime;

    }

}
