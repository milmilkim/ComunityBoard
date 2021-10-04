package Gachon.ComunityBoard.controller.dto;

import Gachon.ComunityBoard.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDTO { // 저장요청할때 쓰는 DTO

    private String title;
    private String writer;
    private String email;
    private String content;
    private String event;
    private int needPeopleNum;
    private String location_x;
    private String location_y;
    private String addressName;
    private String region1Depth;
    private String region2Depth;
    private String placeName;
    private String EventTime;



    @Builder
    public PostsSaveRequestDTO(String title,String content, String event, int needPeopleNumber, String location_x ,String location_y,
                               String addressName,String region1Depth,String region2Depth,String placeName ,String EventTime){
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
        this.EventTime = EventTime;
    }

    public void setWriterAndEamil(String writer,String email) {
        this.writer = writer;
        this.email = email;
    }

    public Posts toEntity(){ // Posts객체를 이 DTO안의 내용을 바탕으로 만들어서 반환
        return Posts.builder()
                .title(title).writer(writer).email(email).content(content).event(event)
                .needPeopleNumber(needPeopleNum).location_x(location_x)
                .location_y(location_y).addressName(addressName).region1Depth(region1Depth)
                .region2Depth(region2Depth).placeName(placeName).eventTime(EventTime)
                .build();
    }

}
