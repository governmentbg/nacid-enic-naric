package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradingScaleDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradingScaleDetailsRepository extends JpaRepository<RudiGradingScaleDetailsEntity, Integer> {
    @Query(" SELECT sg FROM RudiGradingScaleDetailsEntity AS sg  JOIN FETCH sg.gradeEquivalence WHERE sg.gradingScale.id=:gradingScaleId ORDER BY sg.id")
    List<RudiGradingScaleDetailsEntity> getGradingScaleDetailsByGradingScaleId(@Param("gradingScaleId") Integer gradingScaleId);
}
