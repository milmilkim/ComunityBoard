package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.PostsDeleteRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsSaveRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsUpdateRequestDTO;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }



    @After
    public void afterEach(){
        postsRepository.deleteAll();
    }

    // 세션키가져오는부분 없애야 성공공
   @Test
    @WithMockUser(roles = "USER")
    public void 게시글등록() throws Exception{
        //given
        String title = "제목테스트";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        String location_x = "10";
        String location_y = "10";
        String placeName = "가천대학교 운동장";

        PostsSaveRequestDTO saveRequestDTO = PostsSaveRequestDTO.builder()
                .title(title).content(content).event(event)
                .needPeopleNumber(needPeople)
                .locationX(location_x).locationY(location_y)
                .placeName(placeName)
                .build();

        String url = "http://localhost:"+ port+"/api/board/posts";



        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,saveRequestDTO,Long.class);
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(saveRequestDTO)))
                .andExpect(status().isOk());

        //then
//        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 게시글_수정() throws Exception{
        //given
        String title = "제목테스트";
        String writer = "vaaa";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        String location = "10";


        // 저장하고 저장된 Posts를 반환해서 savedPosts에 저장
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople)
                .build());
        System.out.println("createdTime="+savedPosts.getCreatedDate());
        System.out.println("modifiedTime="+savedPosts.getModifiedDate());



        Long updateId = savedPosts.getIdx();
        String updateTitle = "수정된 제목";
        String updateContene = "수정된 내용";
        String updateEvent = "updated BaseBall";
        int updateNum =5;
        String updatelocation ="1";

        String url = "http://localhost:"+ port+"/api/board/posts/"+updateId;

        PostsUpdateRequestDTO updateDTO = PostsUpdateRequestDTO.builder()
                .title(updateTitle).content(updateContene).event(updateEvent).needPeopleNumber(updateNum)
                .location_x(updatelocation).location_y(updatelocation).modifiedEventTime(null)
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
    @WithMockUser(roles = "USER")
    public void 게시글_삭제() throws Exception{
        //given
        String title = "제목테스트";
        String writer = "vaaa";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        String location = "10";


        // 저장하고 저장된 Posts를 반환해서 savedPosts에 저장
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople)
                .build());

        //저장된 게시글의 id를 반아옴
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


//    @Test
//    public void 검색테스트(){
//        //given
//        String title = "제목테스트";
//        String writer = "vaaa";
//        String content = "Content good!";
//        String event = "야구";
//        String event2 = "basketball";
//        int needPeople = 2;
//
//
//        postsRepository.save(Posts.builder()
//                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople)
//                .build());
//        postsRepository.save(Posts.builder()
//                .title(title).writer("김야구").content(content).event(event2).needPeopleNumber(needPeople)
//                .build());
//        postsRepository.save(Posts.builder()
//                .title(title).writer("park").content(content).event(event2).needPeopleNumber(needPeople)
//                .build());
//
//
//        //when
//        String toFineKeyword = "야구";
//
//        List<Posts> postsList = postsRepository.findByKeyword(toFineKeyword);
//
//
//        //then
//        Assertions.assertThat(postsList.get(0).getEvent()).contains(toFineKeyword);
//        System.out.println("postsList = " + postsList);
//
//        Assertions.assertThat(postsList.size()).isEqualTo(2);
//
//    }




}
