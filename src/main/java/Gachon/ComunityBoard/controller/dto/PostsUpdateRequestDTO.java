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
    private int location;
    private LocalDateTime modifiedEventTime;

    @Builder
    public PostsUpdateRequestDTO(String title,String content, String event, int needPeopleNumber, int location, LocalDateTime modifiedEventTime){
        this.title = title;
        this.content = content;
        this.event = event;
        this.needPeopleNum = needPeopleNumber;
        this.location = location;
        this.modifiedEventTime = modifiedEventTime;

    }

}
