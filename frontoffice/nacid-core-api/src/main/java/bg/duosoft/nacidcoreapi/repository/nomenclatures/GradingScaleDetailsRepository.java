package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GradingScaleDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradingScaleDetailsRepository extends JpaRepository<GradingScaleDetailsEntity, Integer> {
    @Query("""
            SELECT gs FROM GradingScaleDetailsEntity gs
            WHERE gs.gradingScale.id = :scaleId
            ORDER BY gs.index ASC,
                     gs.gradeEquivalence.bulgarianGrade DESC,
                     gs.id ASC
            """)
    List<GradingScaleDetailsEntity> getGradingScaleDetailsByScaleId(@Param("scaleId") Integer scaleId);
    List<GradingScaleDetailsEntity> findAllByGradingScaleId(Integer gradingScaleId);
}
