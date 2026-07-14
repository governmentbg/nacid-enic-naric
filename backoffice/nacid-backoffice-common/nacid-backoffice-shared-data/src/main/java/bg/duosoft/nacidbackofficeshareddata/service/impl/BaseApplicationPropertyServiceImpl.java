package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationProperty;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationPropertiesMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseApplicationPropertyRepository;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BaseApplicationPropertyServiceImpl implements BaseApplicationPropertyService {

    private final ApplicationPropertiesMapper mapper;
    private final BaseApplicationPropertyRepository repository;

    @Override
    public ApplicationPropertyDTO selectByType(ApplicationProperty type) {
        if (Objects.isNull(type)) {
            return null;
        }

        return mapper.toDto(repository.selectById(type.code()));
    }
}
