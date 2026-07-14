package bg.duosoft.nacidcoreapi.repository.nomenclatures;


import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataDomainEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.09.2022
 * Time: 12:58
 */
@Repository
public interface ReferenceDataDomainRepository extends BaseRepository<ReferenceDataDomainEntity, String> {

    List<ReferenceDataDomainEntity> getAllByFoOnly(Integer foOnly);

    @Query("SELECT (d.foOnly = 1) as foOnly FROM ReferenceDataDomainEntity d WHERE d.domain = ?1")
    boolean isDomainFoOnly(String domain);

}
