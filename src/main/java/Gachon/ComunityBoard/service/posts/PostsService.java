package Gachon.ComunityBoard.service.posts;


import Gachon.ComunityBoard.controller.dto.*;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
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
        posts.update(updateDTO.getTitle(),updateDTO.getContent(), updateDTO.getEvent(), updateDTO.getNeedPeopleNum(), updateDTO.getLocation(), updateDTO.getModifiedEventTime());
        return idx;
    }


    //게시글 삭제(실제론 삭제x)
    @Transactional
    public void delete(Long idx, PostsDeleteRequestDTO deleteDTO){
        Posts posts = postsRepository.findById(idx)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다 id = "+idx));
        posts.delete(deleteDTO.getDelteYN());
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
    @Transactional
    public List<PostsListResponseDTO> findByKeyword(String keyword){
        return postsRepository.findByKeyword(keyword).stream()
                .map(PostsListResponseDTO::new)
                .collect(Collectors.toList());

    }


}
