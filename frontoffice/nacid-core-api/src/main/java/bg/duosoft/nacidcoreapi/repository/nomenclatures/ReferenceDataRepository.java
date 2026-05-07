package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntityPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceDataRepository extends org.springframework.data.jpa.repository.JpaRepository<ReferenceDataEntity, ReferenceDataEntityPK> {

    List<ReferenceDataEntity> getAllByPkDomainAndActiveOrderByIndexAscNameAsc(String domain, Integer active);

    List<ReferenceDataEntity> getAllByPkDomainOrderByIndexAscNameAsc(String domain);

    @Modifying
    void deleteAllByPkDomain(String domain);

    @Query("SELECT r from ReferenceDataEntity r where r.pk.domain = :domain")
    List<ReferenceDataEntity> getAllByDomain(@Param("domain") String domain);
}
