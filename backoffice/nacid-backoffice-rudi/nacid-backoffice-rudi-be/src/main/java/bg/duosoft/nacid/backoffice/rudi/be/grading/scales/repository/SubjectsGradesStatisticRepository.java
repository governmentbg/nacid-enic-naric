package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SubjectsGradesStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsGradesStatisticRepository extends JpaRepository<SubjectsGradesStatisticEntity, Integer> {
}
