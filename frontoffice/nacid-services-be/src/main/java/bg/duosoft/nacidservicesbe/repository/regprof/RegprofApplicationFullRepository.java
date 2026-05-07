package bg.duosoft.nacidservicesbe.repository.regprof;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CivilIdTypeEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofApplicationFullEntity;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 17:45
 */
public interface RegprofApplicationFullRepository extends FullApplicationRepositoryBase<RegprofApplicationFullEntity> {

    @Query("SELECT r.apostilleApplicationFlag FROM RegprofApplicationFullEntity r WHERE r.id =?1")
    Integer getApostilleApplicationFlag(Integer id);

    @Query("SELECT r.application.applicant.civilIdType FROM RegprofApplicationFullEntity r WHERE r.id =?1")
    CivilIdTypeEntity getApplicantCivilIdTypeForApplication(Integer id);
}
