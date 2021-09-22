package Gachon.ComunityBoard.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; //게시물의 id

    @Column(length = 500, nullable = false)
    private String title;   //게시물 제목


    private String writer;  //게시물 작성자

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //모집 소개글


    private String event;   //운동종목


    private int NeedPeopleNumber;   //필요인원

    @Column(nullable = false)
    private boolean IsRecruiting;      //모집중 여부


    private int location; //좌표값 타입은 다시생각해봐야할듯


    private LocalDateTime EventTime;    //운동할 시간


    private boolean DeleteYn;

    @Builder
    public Posts(String title, String writer,String content, String event, int needPeopleNumber, int location ){
        this.title=title;
        this.writer=writer;
        this.content = content;
        this.event=event;
        this.NeedPeopleNumber = needPeopleNumber;
        this.location = location;
        this.EventTime = null;
        this.IsRecruiting=true;
        this.DeleteYn = false;
    }

    public void update(String title ,String content, String event, int needPeopleNumber, int location){
        // 초기화된값이랑 다른값이들어오면 update된 값이므로 수정을하지만
        //초기화된 값이랑 같은값이들어오면 update가 안된거기때문에 그대로 둔다.
        if(title !=null) this.title=title;
        if(content !=null)this.content = content;
        if(event !=null)this.event=event;
        if(needPeopleNumber !=0)this.NeedPeopleNumber = needPeopleNumber;
        if(location !=0)this.location = location;
    }


}
