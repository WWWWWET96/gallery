package gallery.gallery.auth.config.user;

import gallery.gallery.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 사용자가 없는 경우 null이니까 Optional로 감싸기
     * */
    Optional<User> findByNickname(String nickname);


}
