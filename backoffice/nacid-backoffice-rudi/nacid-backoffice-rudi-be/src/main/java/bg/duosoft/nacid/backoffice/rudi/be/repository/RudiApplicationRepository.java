package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 25.08.2022
 * Time: 14:10
 */
public interface RudiApplicationRepository extends BaseRepository<RudiApplicationEntity, Integer> {

    @Query("SELECT r.efilingId from RudiApplicationEntity r where r.id = :id")
    Integer selectEfilingIdById(@Param("id") Integer id);

    @Query(value = "SELECT 1 FROM common.application where id = :id and ase_code = :subTypeCode", nativeQuery = true)
    Integer checkExists(@Param("id") Integer id, @Param("subTypeCode") String subTypeCode);

    @Query(value = "SELECT 1 FROM common.application where efiling_id = :efilingId", nativeQuery = true)
    Integer isAlreadyAccepted(@Param("efilingId") Integer efilingId);

    @Query(value = "select r.externalSystemId from RudiApplicationEntity r where r.id = :id")
    String getExternalSystemIdById(@Param("id") Integer id);

    @Query("""
                select r
                from RudiApplicationEntity r
                left join r.trainingCourse.baseUniversity.country c
                left join r.trainingCourse.diplomaOwner originOwner
                left join originOwner.originCountry oc
                where r.id <> :id and (
                    (
                        YEAR(r.trainingCourse.diplomaDate) = :diplomaYear
                        and LOWER(c.name) = LOWER(:countryName)
                        and (
                                LOWER(r.trainingCourse.originalEduLevelTranslated) = LOWER(:originalEduLevel)
                             or LOWER(r.trainingCourse.originalEduLevelName) = LOWER(:eduLevel)
                            )
                    )
                    or LOWER(r.trainingCourse.diplomaOwner.civilId) = LOWER(:civilId)
                    or LOWER(r.trainingCourse.diplomaOwnerEan) = LOWER(:diplomaOwnerEan)
                    or (
                            LOWER(r.trainingCourse.diplomaOwner.firstName) = LOWER(:ownerFirstName)
                        and LOWER(r.trainingCourse.diplomaOwner.lastName)  = LOWER(:ownerLastName)
                        and (:birthDate is null OR r.trainingCourse.diplomaOwner.birthDate = TO_DATE(:birthDate, 'YYYY-MM-DD'))
                        and LOWER(oc.name) = LOWER(:birthCountry)
                    )
                )
            """)
    List<RudiApplicationEntity> getAppsWithCommonDiplomasById(
            @Param("id") Integer applicationId,
            @Param("diplomaYear") Integer diplomaYear,
            @Param("countryName") String countryName,
            @Param("eduLevel") String eduLevel,
            @Param("originalEduLevel") String originalEduLevel,
            @Param("civilId") String civilId,
            @Param("ownerFirstName") String ownerFirstName,
            @Param("ownerLastName") String ownerLastName,
            @Param("birthDate") String birthDate,
            @Param("birthCountry") String birthCountry,
            @Param("diplomaOwnerEan") String diplomaOwnerEan
    );

}
