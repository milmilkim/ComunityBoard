package Gachon.ComunityBoard.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.nickname = ?1")
    Optional<User> findByNickname(String nickName);
}
