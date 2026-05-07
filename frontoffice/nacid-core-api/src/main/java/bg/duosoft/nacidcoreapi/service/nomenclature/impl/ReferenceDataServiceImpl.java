package bg.duosoft.nacidcoreapi.service.nomenclature.impl;


import bg.duosoft.nacidcoreapi.repository.nomenclatures.ReferenceDataDomainRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.ReferenceDataRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.ReferenceDataRepositoryCustom;
import bg.duosoft.nacidcoreapi.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.ReferenceDataValidator;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataDomainEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntityPK;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataDomainMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomainDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ReferenceDataFilterDTO;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReferenceDataServiceImpl implements ReferenceDataService {

    private final ReferenceDataRepository repository;
    private final ReferenceDataRepositoryCustom repositoryCustom;
    private final ReferenceDataMapper mapper;
    private final ReferenceDataValidator validator;

    private final ReferenceDataDomainRepository referenceDataDomainRepository;
    private final ReferenceDataDomainMapper referenceDataDomainMapper;

    public List<ReferenceDataDTO> selectAll(String domain, boolean onlyActive) {
        List<ReferenceDataEntity> entities = onlyActive ? repository.getAllByPkDomainAndActiveOrderByIndexAscNameAsc(domain, 1) : repository.getAllByPkDomainOrderByIndexAscNameAsc(domain);
        return mapper.toDtoList(entities);
    }

    public ReferenceDataDTO selectById(String domain, String id) {
        Optional<ReferenceDataEntity> eOpt = repository.findById(new ReferenceDataEntityPK(domain, id));
        return eOpt.map(mapper::toDto).orElse(null);
    }

    public ReferenceDataDTO save(ReferenceDataDTO dto) {
        if (Objects.isNull(dto) || !isDomainFoOnly(dto.getDomain())) {
            throw new BadRequestException();
        }
        BadRequestValidator.validateRequest(validator, dto, true, this);
        ReferenceDataEntity e = repository.save(mapper.toEntity(dto));
        return mapper.toDto(e);
    }

    public ReferenceDataDTO update(ReferenceDataDTO dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getId()) || Objects.isNull(dto.getDomain()) || !isDomainFoOnly(dto.getDomain())) {
            throw new BadRequestException();
        }
        BadRequestValidator.validateRequest(validator, dto, false, this);
        ReferenceDataEntity e = repository.save(mapper.toEntity(dto));
        return mapper.toDto(e);
    }

    public void deleteAll(String domain) {
        if(isDomainFoOnly(domain)) {
            repository.deleteAllByPkDomain(domain);
        } else {
            throw new BadRequestException();
        }
    }

    public void delete(String domain, String id) {
        if(isDomainFoOnly(domain)) {

            ReferenceDataEntity e = repository.findById(new ReferenceDataEntityPK(domain, id)).orElse(null);

            if (Objects.isNull(e)) {
                throw new ResourceNotFoundException();
            }

            repository.delete(e);
        } else {
            throw new BadRequestException();
        }
    }

    public List<ReferenceDataDTO> selectFoReferenceData(ReferenceDataFilterDTO filter) {
        List<ReferenceDataEntity> result = repositoryCustom.selectFoReferenceData(filter);
        return mapper.toDtoList(result);
    }

    public int selectFoReferenceDataCount(ReferenceDataFilterDTO filter) {
        return repositoryCustom.selectFoReferenceDataCount(filter);
    }

    public List<ReferenceDataDTO> selectByDomain(String domain) {
        List<ReferenceDataEntity> entities = repository.getAllByDomain(domain);
        return mapper.toDtoList(entities);
    }

    public List<ReferenceDataDomainDTO> getFoReferenceDataDomains(){
        List<ReferenceDataDomainEntity> entityList = referenceDataDomainRepository.getAllByFoOnly(1);
        return referenceDataDomainMapper.toDtoList(entityList);
    }

    public boolean isDomainFoOnly(String domain){
        if(domain != null) {
            return referenceDataDomainRepository.isDomainFoOnly(domain);
        } else {
            return true;
        }
    }

}
