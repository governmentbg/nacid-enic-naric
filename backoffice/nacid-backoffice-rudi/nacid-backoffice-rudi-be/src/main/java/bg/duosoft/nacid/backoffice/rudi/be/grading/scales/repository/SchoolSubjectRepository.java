package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SchoolSubjectEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolSubjectRepository extends JpaRepository<SchoolSubjectEntity, Integer> {

    @Query("SELECT distinct ss FROM SchoolSubjectEntity AS ss WHERE lower(ss.subjectBg)=lower(:subjectBg)")
    SchoolSubjectEntity getSubjectBySubjectBg(@Param("subjectBg") String subjectBg);

    @Query("SELECT s FROM SchoolSubjectEntity s WHERE LOWER(s.subjectBg) LIKE LOWER(CONCAT('%', :subjectBg, '%'))")
    List<SchoolSubjectEntity> findAutocompleteBySubjectBg(String subjectBg, Pageable pageable);
}
