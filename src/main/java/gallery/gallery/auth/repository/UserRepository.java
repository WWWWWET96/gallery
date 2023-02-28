package gallery.gallery.auth.repository;

import gallery.gallery.general.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 사용자가 없는 경우 null이니까 Optional로 감싸기
     * 
   @Query("select u from User as u where u.nickname = :nickname and u.password = :password")
    Optional<User> findByLoginDto(@Param("nickname") String nickname, @Param("password") String password);
     */
    @Query("select count(u.id) = 1 from User as u where " +
            "u.email = :email")
    Boolean existsByEmail(@Param("email")String email);

    Optional<User> findByNickname(String nickname);
}
