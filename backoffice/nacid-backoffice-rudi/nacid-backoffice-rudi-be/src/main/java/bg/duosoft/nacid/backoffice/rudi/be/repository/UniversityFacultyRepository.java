package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityFacultyRepository extends BaseRepository<FacultyEntity, Integer> {

    @Query("SELECT f FROM FacultyEntity f where f.university.id = :uniId and trim(both from f.name) = trim(both from :facultyName)")
    List<FacultyEntity> selectFacultiesByUniversityIdAndFacultyName(@Param("uniId") Integer universityId, @Param("facultyName") String facultyName);

    @Query("SELECT f FROM FacultyEntity f where f.id = :facultyId")
    FacultyEntity selectFacultyById(@Param("facultyId") Integer facultyId);

    @Query(value = "SELECT v.faculty_id as facultyId, count(*) as usageCount FROM rudi.training_course_universities v where v.faculty_id in :facultyIdentifiers group by (v.faculty_id)", nativeQuery = true)
    List<FacultyUsageCount> getUsageCountForFaculties(@Param("facultyIdentifiers") List<Integer> facultyIdentifiers);

    interface FacultyUsageCount {

        Integer getFacultyId();

        Integer getUsageCount();

    }
}
