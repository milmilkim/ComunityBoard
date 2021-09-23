package Gachon.ComunityBoard.service.posts;


import Gachon.ComunityBoard.controller.dto.PostsDeleteRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsResponseDTO;
import Gachon.ComunityBoard.controller.dto.PostsSaveRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsUpdateRequestDTO;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //게시글 등록
    @Transactional
    public Long save(PostsSaveRequestDTO saveDTO){
        return postsRepository.save(saveDTO.toEntity()).getIdx();
    }

    //게시글 조회
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



}
