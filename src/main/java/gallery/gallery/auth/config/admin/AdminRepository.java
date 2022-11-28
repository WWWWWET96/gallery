package gallery.gallery.auth.config.admin;

import gallery.gallery.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long > {
    Optional<Admin> findByNickname(String nickname);
}
