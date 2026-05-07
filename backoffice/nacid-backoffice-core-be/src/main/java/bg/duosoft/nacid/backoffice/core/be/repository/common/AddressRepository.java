package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.AddressSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface AddressRepository extends BaseRepository<AddressEntity, Integer>, AddressSearchRepository {


}
