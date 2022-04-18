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
    @Query("SELECT p FROM Posts p WHERE p.deleteYn = false AND p.isRecruiting = true")
    List<Posts> findAllNotDeleted();


    // 제목, 작성자, 운동종목으로 조회 (삭제안된거만 조회)
    @Query("SELECT p FROM Posts p WHERE p.title LIKE %:keyword% OR p.writer LIKE %:keyword% OR p.event LIKE %:keyword% AND p.deleteYn = false")
    List<Posts> findByKeyword(@Param("keyword") String keyword);

    //Paging 함수선
    @Override
    @Query("SELECT p FROM Posts p WHERE p.deleteYn = false")
    Page<Posts> findAll(Pageable pageable);

    // 검색된 게시물들 페이징 제목,이름,종목,지역
    @Query("SELECT p FROM Posts p WHERE p.title LIKE %:keyword% OR p.writer LIKE %:keyword% " +
            "OR p.event LIKE %:keyword% OR p.addressName LIKE %:keyword% OR p.placeName LIKE %:keyword% AND p.deleteYn = false")
    Page<Posts> findByKeywordPaging(@Param("keyword") String keyword,Pageable pageable);
}
