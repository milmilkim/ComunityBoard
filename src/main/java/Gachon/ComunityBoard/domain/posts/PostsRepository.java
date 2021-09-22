package Gachon.ComunityBoard.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.idx DESC")
    List<Posts> findAllDesc();

    @Query("SELECT p FROM Posts p WHERE p.DeleteYn = false")
    List<Posts> findAllNotDeleted();

}
