package bg.duosoft.nacid.backoffice.core.be.repository.common.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.AddressFilterDTO;

import java.util.List;

public interface AddressSearchRepository {

    List<AddressEntity> searchRecords(AddressFilterDTO filter);

    int getRecordsCount(AddressFilterDTO filter);

}
