package gallery.gallery.repository;

<<<<<<< main:src/main/java/gallery/gallery/repository/ArtRepository.java
import gallery.gallery.domain.Art;
=======
import gallery.gallery.common.enums.Selling;
import gallery.gallery.general.domain.Art;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> feat: [art] 페이징 기능(#17):src/main/java/gallery/gallery/general/repository/ArtRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    Page<Art> findAll(Pageable pageable);

    Page<Art> findByAuthorContaining(String searchKeyword, Pageable pageable);

    Page<Art> findByArtNameContaining(String searchKeyword, Pageable pageable);
    @Query("select a from Art as a where a.isSelling = :searchKeyword")
    Page<Art> findByIsSelling(@Param("searchKeyword") Selling searchKeyword, Pageable pageable);
////    @Query("select a from Art as a where a.author = :searchKeyword")
//    Page<Art> findByAuthor(@Param("searchKeyword")String searchKeyword, Pageable pageable);

  
}
