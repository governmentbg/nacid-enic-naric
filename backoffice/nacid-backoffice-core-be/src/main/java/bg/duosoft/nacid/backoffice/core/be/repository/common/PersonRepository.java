package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.PersonSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends BaseRepository<PersonEntity, Integer>, PersonSearchRepository {

    @Query("select p from PersonEntity p where p.civilId = :civilId and (:civilIdType is null or p.civilIdType.id = :civilIdType) and (:active is null or p.isActive = :active) and (:foreignIdentifierType is null or :foreignIdentifierType = p.foreignIdentifierType.pk.id) and (:foreignIdentifierCountry is null or :foreignIdentifierCountry = p.foreignIdentifierCountry.id)")
    List<PersonEntity> findByCivilIdAndCivilIdTypeAndIsActive(@Param("civilIdType") String civilIdType, @Param("civilId") String civilId, @Param("foreignIdentifierType") String foreignIdentifierType, @Param("foreignIdentifierCountry") String foreignIdentifierCountry, @Param("active") Integer active);

    @Query(value = "SELECT v.person_id as personId, count(*) as connectedAppsCount FROM common.vw_applications_by_person v where v.person_id in :personIdentifiers group by (v.person_id)", nativeQuery = true)
    List<AppsPerPersonResult> getApplicationsCountForPersons(@Param("personIdentifiers") List<Integer> personIdentifiers);

    interface AppsPerPersonResult {

        Integer getPersonId();

        Integer getConnectedAppsCount();

    }

}
