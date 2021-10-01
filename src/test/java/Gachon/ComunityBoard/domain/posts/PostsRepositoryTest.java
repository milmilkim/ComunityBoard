package Gachon.ComunityBoard.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void read_all(){
        //given
        String title = "제목테스트";
        String writer = "vaaa";
        String content = "Content good!";
        String event = "baseball";
        int needPeople = 2;
        double location = 10;

        postsRepository.save(Posts.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople).location_x(location).location_y(location)
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();


        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getWriter()).isEqualTo(writer);


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
//        double location = 10;
//
//        postsRepository.save(Posts.builder()
//                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople).location_x(location).location_y(location)
//                .build());
//        postsRepository.save(Posts.builder()
//                .title(title).writer("김야구").content(content).event(event2).needPeopleNumber(needPeople).location_x(location).location_y(location)
//                .build());
//        postsRepository.save(Posts.builder()
//                .title(title).writer("park").content(content).event(event2).needPeopleNumber(needPeople).location_x(location).location_y(location)
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
