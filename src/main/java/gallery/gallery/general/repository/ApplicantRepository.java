package gallery.gallery.general.repository;

import gallery.gallery.general.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    @Query("select count(a.id) = 1 from Applicant as a where " +
            "a.user.id = :userId and a.art.id = :artId")
    Boolean existsApplicant(@Param("userId")Long userId, @Param("artId") Long artId);
}
