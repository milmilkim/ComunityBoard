package Gachon.ComunityBoard.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    // 역순으로 조회
    @Query("SELECT p FROM Posts p ORDER BY p.idx DESC")
    List<Posts> findAllDesc();

    // Delete_YN이 false인 값만 조회(삭제 안된것만 조회)
    @Query("SELECT p FROM Posts p WHERE p.DeleteYn = false")
    List<Posts> findAllNotDeleted();


    // 제목, 작성자, 운동종목으로 조회 (삭제안된거만 조회)
    @Query("SELECT p FROM Posts p WHERE p.title LIKE %:keyword% OR p.writer LIKE %:keyword% OR p.event LIKE %:keyword% AND p.DeleteYn = false")
    List<Posts> findByKeyword(@Param("keyword") String keyword);

    //Paging 함수선
    @Override
    @Query("SELECT p FROM Posts p WHERE p.DeleteYn = false")
    Page<Posts> findAll(Pageable pageable);

    // 잠깐 테스트용 => 검색된 게시물들 페이징
    @Query("SELECT p FROM Posts p WHERE p.title LIKE %:keyword% OR p.writer LIKE %:keyword% OR p.event LIKE %:keyword% AND p.DeleteYn = false")
    Page<Posts> findByKeywordPaging(@Param("keyword") String keyword,Pageable pageable);
}
