package Gachon.ComunityBoard.service.posts;


import Gachon.ComunityBoard.controller.dto.PostsDeleteRequestDTO;
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

    @Transactional
    public Long save(PostsSaveRequestDTO saveDTO){
        return postsRepository.save(saveDTO.toEntity()).getIdx();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO updateDTO){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(updateDTO.getTitle(),updateDTO.getContent(), updateDTO.getEvent(), updateDTO.getNeedPeopleNum(), updateDTO.getLocation());
        return id;
    }

    @Transactional
    public void delete(Long id, PostsDeleteRequestDTO deleteDTO){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다 id = "+id));
        posts.delete(deleteDTO.getDelteYN());
    }



}
