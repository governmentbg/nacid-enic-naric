package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.repository.common.AddressRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.AddressService;
import bg.duosoft.nacid.backoffice.core.be.validation.common.AddressValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.AddressFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.AddressMapper;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper mapper;
    private final AddressRepository repository;

    @Override
    public AddressDTO selectById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        AddressEntity personEntity = repository.findById(id).orElse(null);
        return mapper.toDto(personEntity);
    }

    @Override
    @LogObjectChange(id = "#result.id", before = "#root.target.selectById(#addressDTO.id)", after = "#result", operation = "#addressDTO.id == null ? 'create' : 'update'")
    public AddressDTO save(AddressDTO addressDTO, AddressValidator validator) {
        mapper.overrideDtoData(addressDTO);

        List<ValidationError> errors = validator.validate(addressDTO);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        AddressEntity addressEntity = mapper.toEntity(addressDTO);
        return mapper.toDto(repository.save(addressEntity));
    }

    @Override
    public List<AddressDTO>  searchRecords(AddressFilterDTO filter) {
        List<AddressEntity> addressEntities = repository.searchRecords(filter);
        return mapper.toDtoList(addressEntities);
    }

}
