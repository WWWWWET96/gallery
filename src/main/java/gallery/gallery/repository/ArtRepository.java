package gallery.gallery.repository;

import gallery.gallery.domain.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

    Optional<Art> findById(Long id);
}
