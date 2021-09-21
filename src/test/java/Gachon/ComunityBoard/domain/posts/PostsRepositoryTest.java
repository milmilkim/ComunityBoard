package Gachon.ComunityBoard.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

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
        int location = 10;

        postsRepository.save(Posts.builder()
                .title(title).writer(writer).content(content).event(event).needPeopleNumber(needPeople).location(location)
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();


        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getWriter()).isEqualTo(writer);


    }




}
