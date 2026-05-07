package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseUniversityExaminationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingCourseUniversityExaminationRepository extends BaseRepository<TrainingCourseUniversityExaminationEntity, Integer> {

    @Query("SELECT u FROM TrainingCourseUniversityExaminationEntity u where u.university.id = :universityId")
    List<TrainingCourseUniversityExaminationEntity> selectUniversityExaminationsByUniversity(@Param("universityId") Integer universityId);
}
