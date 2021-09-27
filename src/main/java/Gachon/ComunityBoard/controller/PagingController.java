package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.PostsListResponseDTO;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@RestController
public class PagingController {

    private final PostsRepository postsRepository;

//    @GetMapping("/api/board")
//    public Page<PostsListResponseDTO> getAllPosts(){
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        return postsRepository.findAll(pageRequest);
//    }
    @GetMapping("/api/board")
    public Page<PostsListResponseDTO> paging(@PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
        Page<Posts> postsList = postsRepository.findAll(pageRequest);

        Page<PostsListResponseDTO> pagingList = postsList.map(
                posts -> new PostsListResponseDTO(
                        posts
                )
        );
        return pagingList;
    }
    ////http://localhost:8080/api/board?page=0&size=5 이런식으로 호출


    // 더미데이터 생성
    @PostConstruct
    public void initializing(){

        for (int i = 0; i < 100; i++) {
            Posts posts = Posts.builder()
                    .title(i+"번 게시글")
                    .content(i+"내용내용")
                    .writer("김승환")
                    .build();
            postsRepository.save(posts);
        }
    }


}
