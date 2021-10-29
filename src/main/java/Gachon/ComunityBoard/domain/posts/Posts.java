package Gachon.ComunityBoard.domain.posts;

import Gachon.ComunityBoard.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; //게시물의 id

    @Column(length = 500, nullable = false)
    private String title;   //게시물 제목

    private String writer;  //게시물 작성자

    private String userPicture; // 작성자 사진

    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //모집 소개글

    private String event;   //운동종목

    private int needPeopleNum;   //필요인원

    @Column(nullable = false)
    private boolean isRecruiting;      //모집중 여부

    // 운동할 위치 좌표값
    private String locationX;
    private String locationY;
    // 운동위치 지역명
    private String addressName; //"전북 익산시 부송동 100"
    // 00시
    private String region1Depth;
    // 00구
    private String region2Depth;
    // 장소이름
    private String placeName;
    // 프론트에서 날짜와 시간선택으로 String으로 받아옴
    private String eventTime;    //운동할 시간
    //삭제여부
    private boolean deleteYn;

    @Builder
    public Posts(String title, String writer,String email,String content, String event
            ,int needPeopleNum, String locationX,String locationY
            ,String addressName,String region1Depth,String region2Depth,String placeName
            , String eventTime, String userPicture){
        this.title=title;
        this.writer=writer;

        this.userPicture = userPicture;
        this.email = email;
        this.content = content;
        this.event=event;
        this.needPeopleNum = needPeopleNum;
        this.locationX = locationX;
        this.locationY = locationY;
        this.addressName = addressName;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
        this.placeName = placeName;
        this.eventTime = eventTime;
        this.isRecruiting=true;
        this.deleteYn = false;
    }

    public void update(String title ,String content, String event, int needPeopleNum, String locationX,String locationY,
                       String addressName,String region1Depth,String region2Depth,String placeName,String eventTime){
        // 초기화된값이랑 다른값이들어오면 update된 값이므로 수정을하지만
        //초기화된 값이랑 같은값이들어오면 update가 안된거기때문에 그대로 둔다.
        this.title=title;
        this.content = content;
        this.event=event;
        this.needPeopleNum = needPeopleNum;
        this.locationX = locationX;
        this.locationY = locationY;
        this.addressName = addressName;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
        this.placeName = placeName;
        this.eventTime = eventTime;

    }

    public void delete(boolean deleteYn){
        this.deleteYn = deleteYn;
    }

    public void endRecruiting(boolean isRecruiting){
        this.isRecruiting = isRecruiting;
    }


}
