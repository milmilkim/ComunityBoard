package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.PostsSaveRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsUpdateRequestDTO;
import Gachon.ComunityBoard.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
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

    // 게시글 수정
    @PutMapping("/api/board/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDTO updateDTO){
        return postsService.update(id,updateDTO);
    }




}
