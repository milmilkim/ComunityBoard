package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.config.auth.LoginUser;
import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.controller.dto.PostsListResponseDTO;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PagingController {

    private final PostsRepository postsRepository;

//    @GetMapping("/api/board")
//    public Page<PostsListResponseDTO> getAllPosts(){
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        return postsRepository.findAll(pageRequest);
//    }

    // 잠시만 동결 PostsService로 이동
    //전체 게시물 역순으로보여줌
    @ApiOperation(value = "전체게시물 조회(페이징)",notes = "전체 게시물을 한페이지에 12개씩 생성시간 역순으로 보여줍니다")
    @GetMapping("/api/board")
    public Page<PostsListResponseDTO> paging(@PageableDefault(size = 12, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
        Page<Posts> postsList = postsRepository.findAll(pageRequest);

        Page<PostsListResponseDTO> pagingList = postsList.map(
                posts -> new PostsListResponseDTO(
                        posts
                )
        );
        return pagingList;
    }
    ////http://localhost:8080/api/board?page=0&size=5 이런식으로 호출

    @ApiOperation(value = "게시물 검색",notes = "키워드에 해당하는 게시물을 조회합니다(제목,작성자,지역,종목)")
    @GetMapping("/api/board/search/{keyword}")
    public Page<PostsListResponseDTO> searchedPaging(@PathVariable("keyword") String keyword, @PageableDefault(size = 12, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
        Page<Posts> postsList = postsRepository.findByKeywordPaging(keyword,pageRequest);

        Page<PostsListResponseDTO> pagingList = postsList.map(
                posts -> new PostsListResponseDTO(
                        posts
                )
        );
        return pagingList;
    }

    @GetMapping("/api/board/all")
    public List<PostsListResponseDTO> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDTO::new)
                .collect(Collectors.toList());
    }

    //http://localhost:8080/api/board/search/야구?page=2
    //http://localhost:8080/api/board/search/야구
    //@Param으로했다가 2시간삽질하고 @PathVariable로바꿔서해결 JPA만세세


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
        for (int i = 0; i < 13; i++) {
            Posts posts = Posts.builder()
                    .title(i+"번 게시글")
                    .content(i+"내용내용")
                    .writer("김승환")
                    .event("야구야구")
                    .build();
            postsRepository.save(posts);
        }
    }


}
