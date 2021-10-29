package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.config.auth.LoginUser;
import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.controller.dto.*;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import Gachon.ComunityBoard.domain.user.User;
import Gachon.ComunityBoard.domain.user.UserRepository;
import Gachon.ComunityBoard.service.posts.PostsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final PostsService postsService;
    private final PostsRepository postsRepository;

    // 게시글 등록
    @ApiOperation(value = "게시글 등록",notes = "게시글을 등록합니다")
    @PostMapping("/api/board/posts")
    public Long save(@RequestBody PostsSaveRequestDTO saveDTO){

        //저장 요청하는 유저의 이메일을 가져옴
        String userEmail = saveDTO.getEmail();
        // 그 이메일로 유저의 DB정보를가져옴
        User userDB = userRepository.findByUserEmail(userEmail);
        // 가저온 DB정보안에있는 해당유저의 닉네임과 이메일을 가져와서 DTO에 추가해줌
        saveDTO.setWriterAndEamil(userDB.getNickname(),userDB.getPicture(),userDB.getId());
        return postsService.save(saveDTO);
    }

    //    public PostsResponseDTO findById(@PathVariable Long idx,@LoginUser SessionUser user){
    // 게시글 조회
    @ApiOperation(value = "게시글 조회",notes = "idx에 해당하는 게시물을 조회합니다")
    @GetMapping("/api/board/posts/{idx}")
    public PostsResponseDTO findById(@PathVariable Long idx){
//        SessionUser user = (SessionUser) session.getAttribute("user");
//        String postsEmail = postsRepository.findById(idx).get().getEmail();

        PostsResponseDTO postsResponseDTO = postsService.findById(idx);
        // 만약 같으면 자신이쓴글이라는거랑 같이 리턴
//        if(user.getEmail().equals(postsEmail)){
//            postsResponseDTO.setMine(true);
//            return postsResponseDTO;
//        }else {
//            postsResponseDTO.setMine(false);
//            return postsResponseDTO;
//        }
        postsResponseDTO.setMine(false);
        return postsResponseDTO;

    }


    // 게시글 수정
    @ApiOperation(value = "게시글 수정",notes = "idx에 해당하는 게시물을 넘겨받은 DTO를 기반으로 수정합니다")
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
    // 게시글 삭제
    @ApiOperation(value = "게시글 삭제",notes = "idx에 해당하는 게시글을 삭제합니다")
    @PutMapping("/api/board/posts/{idx}/isDelete")
    public Long delete(@PathVariable Long idx){
        PostsDeleteRequestDTO deleteDTO = new PostsDeleteRequestDTO();
        postsService.delete(idx, deleteDTO);
        return idx;
    }

    @ApiOperation(value = "게시물 모집 종료",notes = "idx에 해당하는 게시물의 모집을 종료합니다.")
    @PutMapping("/api/board/posts/{idx}/endRecruiting")
    public Long endRecruiting(@PathVariable Long idx){
        PostsEndRecruitingDTO recruitingDTO = new PostsEndRecruitingDTO();

        postsService.endRecruiting(idx, recruitingDTO);
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
