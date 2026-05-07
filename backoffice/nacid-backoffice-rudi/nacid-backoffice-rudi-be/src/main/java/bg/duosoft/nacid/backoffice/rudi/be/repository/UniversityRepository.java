package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.UniversityRepositoryCustom;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityRepository extends BaseRepository<UniversityEntity, Integer>, UniversityRepositoryCustom {
    @Query("SELECT r.id, r.bgName, r.orgName, r.active from UniversityEntity r where r.address.country.id = :countryCode")
    List<Object[]> selectByCountryCode(@Param("countryCode") String countryCode);

    @Query("SELECT DISTINCT tcu.pk.university.id FROM TrainingCourseUniversityEntity tcu " +
            "where tcu.ordNum = 1 and tcu.pk.trainingCourse.id = :id")
    List<Integer> selectBaseUniIdByTrainingCourse(@Param("id") Integer id);

    @Query("SELECT DISTINCT tcu.pk.university.id FROM TrainingCourseUniversityEntity tcu " +
            "where tcu.ordNum <> 1 and tcu.pk.trainingCourse.id = :id")
    List<Integer> selectSecondaryUniIdsByTrainingCourse(@Param("id") Integer id);

    @Query("SELECT f FROM UniversityEntity u JOIN u.faculties f WHERE u.id = :uniId and LOWER(f.name) like :name and f.isActive in :activeVals")
    List<FacultyEntity> selectFacultiesByUniIdAndName(@Param("uniId") Integer universityId, @Param("name") String name, @Param("activeVals") List<Integer> activeVals, Pageable pageable);

    @Query("SELECT u.faculties FROM UniversityEntity u where u.id = :uniId")
    List<FacultyEntity> selectUniversityFacultiesByUniversityId(@Param("uniId") Integer universityId);

    @Query("SELECT u.bgName FROM UniversityEntity u where u.id = :uniId")
    String selectUniversityNameById(@Param("uniId") Integer universityId);

    @Query("SELECT u FROM UniversityEntity u where u.bgName = :bgName")
    List<UniversityEntity> selectUniversityByBgNameExact(@Param("bgName") String bgName);
}
