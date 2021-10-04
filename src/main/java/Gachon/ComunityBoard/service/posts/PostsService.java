package Gachon.ComunityBoard.service.posts;


import Gachon.ComunityBoard.controller.dto.*;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //게시글 등록
    @Transactional
    public Long save(PostsSaveRequestDTO saveDTO){
        return postsRepository.save(saveDTO.toEntity()).getIdx();
    }

    // idx로 게시글 조회
    public PostsResponseDTO findById(Long idx){
        Posts post = postsRepository.findById(idx)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. idx = "+idx));
        return new PostsResponseDTO(post);
    }



    //게시글 수정
    @Transactional
    public Long update(Long idx, PostsUpdateRequestDTO updateDTO){
        Posts posts = postsRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. idx = "+idx));
        posts.update(updateDTO.getTitle(),updateDTO.getContent(), updateDTO.getEvent(),
                updateDTO.getNeedPeopleNum(), updateDTO.getLocation_x(),updateDTO.getLocation_y(),
                updateDTO.getAddressName(),updateDTO.getRegion1Depth(),updateDTO.getRegion2Depth(),
                updateDTO.getPlaceName(), updateDTO.getModifiedEventTime());
        return idx;
    }


    //게시글 삭제(실제론 삭제x)
    @Transactional
    public void delete(Long idx, PostsDeleteRequestDTO deleteDTO){
        Posts posts = postsRepository.findById(idx)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다 id = "+idx));
        posts.delete(deleteDTO.getDelteYN());
    }

    @Transactional
    public void endRecruiting(Long idx, PostsEndRecruitingDTO recruitingDTO){
        Posts posts = postsRepository.findById(idx)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다 id = "+idx));
        posts.endRecruiting(recruitingDTO.getIsRecruiting());
    }




    // 이 밑으론 아직 테스트 못해봄


    // 게시글 전체조회(역순) -> 아직 컨트롤러 못만듬 (아마 index페이지에 쓸듯)
    @Transactional(readOnly = true)
    public List<PostsListResponseDTO> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 게시글 검색 -> 아직 컨트롤러 못만듬.
//    @Transactional
//    public List<PostsListResponseDTO> findByKeyword(String keyword){
//        return postsRepository.findByKeyword(keyword).stream()
//                .map(PostsListResponseDTO::new)
//                .collect(Collectors.toList());
//
//    }

    //paging하는걸 어디에둬야하나
//    @Transactional
//    public Page<PostsListResponseDTO> paging(@PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
//        Page<Posts> postsList = postsRepository.findAll(pageRequest);
//
//        Page<PostsListResponseDTO> pagingList = postsList.map(
//                posts -> new PostsListResponseDTO(
//                        posts
//                )
//        );
//        return pagingList;
//    }

//    @Transactional
//    public Page<PostsListResponseDTO> SearchingPaging(String keyword,@PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
//        Page<Posts> postsList = postsRepository.findByKeyword(keyword,pageRequest);
//
//        Page<PostsListResponseDTO> pagingList = postsList.map(
//                posts -> new PostsListResponseDTO(
//                        posts
//                )
//        );
//        return pagingList;
//    }


}
//http://localhost:8080/api/board?page=0&size=5