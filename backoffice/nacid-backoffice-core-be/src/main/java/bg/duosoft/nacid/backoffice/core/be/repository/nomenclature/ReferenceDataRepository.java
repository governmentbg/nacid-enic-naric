package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ReferenceDataRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:16
 */
public interface ReferenceDataRepository extends BaseRepository<ReferenceDataEntity, ReferenceDataEntityPK>, ReferenceDataRepositoryCustom {

    List<ReferenceDataEntity> getAllByPkDomainAndActiveOrderByIndexAscNameAsc(String domain, Integer active);

    List<ReferenceDataEntity> getAllByPkDomainOrderByIndexAscNameAsc(String domain);

    @Modifying
    void deleteAllByPkDomain(String domain);

    @Query("SELECT r from ReferenceDataEntity r where r.pk.domain = :domain")
    List<ReferenceDataEntity> getAllByDomain(@Param("domain") String domain);
}
