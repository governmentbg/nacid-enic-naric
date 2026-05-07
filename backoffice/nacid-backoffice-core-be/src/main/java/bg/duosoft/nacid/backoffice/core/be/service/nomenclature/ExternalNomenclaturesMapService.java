package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;


import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ExternalNomenclaturesMapRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ExternalNomenclaturesMapEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ExternalNomenclaturesMapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
@Service
@RequiredArgsConstructor
@Transactional
public class ExternalNomenclaturesMapService {
    protected final ExternalNomenclaturesMapRepository repository;
    protected final ExternalNomenclaturesMapMapper mapper;

    public List<ExternalNomenclaturesMapDTO> selectBySystemNomenclatureTypeInternalNomId(String system, String nomenclatureType, String internalNomId) {
        return mapper.toDtoList(repository.getAllBySystemAndNomenclatureTypeAndInternalNomId(system, nomenclatureType, internalNomId));
    }

    public ExternalNomenclaturesMapDTO save(ExternalNomenclaturesMapDTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }

        ExternalNomenclaturesMapEntity e = repository.save(mapper.toEntity(dto));
        return mapper.toDto(e);
    }

    public ExternalNomenclaturesMapDTO update(ExternalNomenclaturesMapDTO dto) {
        if (dto.getId() == null || !repository.existsById(dto.getId())) {
            throw new ResourceNotFoundException();
        }
        return save(dto);
    }
    
    public void deleteBySystem(String system) {
        repository.deleteAllBySystem(system);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
