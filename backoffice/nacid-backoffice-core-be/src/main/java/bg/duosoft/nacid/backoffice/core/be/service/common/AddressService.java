package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.be.validation.common.AddressValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.AddressFilterDTO;
import java.util.List;

public interface AddressService {

    AddressDTO selectById(Integer id);

    AddressDTO save(AddressDTO addressDTO, AddressValidator validator);

    List<AddressDTO> searchRecords(AddressFilterDTO filter);

}
