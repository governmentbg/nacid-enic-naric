package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradeEquivalenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeEquivalenceRepository extends JpaRepository<RudiGradeEquivalenceEntity, Integer> {
}
