package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.be.repository.common.ApplicationPropertiesRepository;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationPropertiesMapper;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 14:17
 */
@Service
@RequiredArgsConstructor
public class ApplicationPropertiesService extends CrudServiceBaseImpl<String, ApplicationPropertyDTO> {
    private final ApplicationPropertiesRepository repository;
    private final ApplicationPropertiesMapper mapper;
    @Override
    protected ApplicationPropertiesRepository getRepository() {
        return repository;
    }

    @Override
    protected ApplicationPropertiesMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<ApplicationPropertyDTO> getValidator() {
        return null;
    }
}
