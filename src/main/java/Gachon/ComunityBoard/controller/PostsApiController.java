package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.*;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 게시글 등록
    @PostMapping("/api/board/posts")
    public Long save(@RequestBody PostsSaveRequestDTO saveDTO){
        return postsService.save(saveDTO);
    }


    // 게시글 조회
    @GetMapping("/api/board/posts/{idx}")
    public PostsResponseDTO findById(@PathVariable Long idx){
        return postsService.findById(idx);
    }


    // 게시글 수정
    @PutMapping("/api/board/posts/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody PostsUpdateRequestDTO updateDTO){
        return postsService.update(idx,updateDTO);
    }


    //게시글 삭제
//    @PutMapping("/api/board/posts/{idx}/isDelete")
//    public Long delete(@PathVariable Long idx, @RequestBody PostsDeleteRequestDTO deleteDTO){
//        postsService.delete(idx, deleteDTO);
//        return idx;
//    }
    @PutMapping("/api/board/posts/{idx}/isDelete")
    public Long delete(@PathVariable Long idx){
        PostsDeleteRequestDTO deleteDTO = new PostsDeleteRequestDTO();
        postsService.delete(idx, deleteDTO);
        return idx;
    }


    // 임시로 해보는것들

//    @GetMapping("/api/board/posts/{keyword}")
//    public PostsListResponseDTO

//    @GetMapping("/api/board")
//    public Page<PostsListResponseDTO> paging(@PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
//        Page<PostsListResponseDTO> pagingList =postsService.paging()
//        return pagingList;
//    }










}
