package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.PostsDeleteRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsSaveRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsUpdateRequestDTO;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void afterEach(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글등록() throws Exception{
        //given
        String title = "제목테스트";
        String writer = "vaaa";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        int location = 10;

        PostsSaveRequestDTO saveRequestDTO = PostsSaveRequestDTO.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople).location(location)
                .build();

        String url = "http://localhost:"+ port+"/api/board/posts";



        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,saveRequestDTO,Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void 게시글_수정() throws Exception{
        //given
        String title = "제목테스트";
        String writer = "vaaa";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        int location = 10;


        // 저장하고 저장된 Posts를 반환해서 savedPosts에 저장
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople).location(location).eventTime(null)
                .build());
        System.out.println(savedPosts.getCreatedDate());



        Long updateId = savedPosts.getIdx();
        String updateTitle = "수정된 제목";
        String updateContene = "수정된 내용";
        String updateEvent = "updated BaseBall";
        int updateNum =5;
        int updatelocation =1;

        String url = "http://localhost:"+ port+"/api/board/posts/"+updateId;

        PostsUpdateRequestDTO updateDTO = PostsUpdateRequestDTO.builder()
                .title(updateTitle).content(updateContene).event(updateEvent).needPeopleNumber(updateNum).location(updatelocation).modifiedEventTime(null)
                .build();

        HttpEntity<PostsUpdateRequestDTO> updatedEntity = new HttpEntity<>(updateDTO);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,updatedEntity,Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        System.out.println("all is empty?"+ all.isEmpty());

        //수정된 부분
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(updateContene);
        Assertions.assertThat(all.get(0).getNeedPeopleNumber()).isEqualTo(updateNum);
    }


    @Test
    public void 게시글_삭제() throws Exception{
        //given
        String title = "제목테스트";
        String writer = "vaaa";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        int location = 10;


        // 저장하고 저장된 Posts를 반환해서 savedPosts에 저장
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople).location(location)
                .build());

        Long Id = savedPosts.getIdx();
        PostsDeleteRequestDTO deleteDTO = new PostsDeleteRequestDTO();
        String url = "http://localhost:"+ port+"/api/board/posts/"+Id+"/isDelete";


        //delete_yn값을 true로 바꿔줌
        HttpEntity<PostsDeleteRequestDTO> deletedEntity = new HttpEntity<>(deleteDTO);
        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,deletedEntity,Long.class);


        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // all에는 Delete_yn이 false인것만 검색
        List<Posts> all = postsRepository.findAllNotDeleted();
        System.out.println("all is empty?"+ all.isEmpty());

        //만약 비어있는게 true면 통과
        Assertions.assertThat(all.isEmpty()).isEqualTo(true);

    }




}
