package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata.ReferenceDataClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.settlement.SettlementClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.UniversityFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.FacultyMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.UniversityMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.UniversityFacultyRepository;
import bg.duosoft.nacid.backoffice.rudi.be.repository.UniversityRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.UniversityValidator;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UniversityServiceImpl extends CrudServiceBaseImpl<Integer, UniversityDTO> implements UniversityService {
    private final UniversityRepository repository;
    private final UniversityFacultyRepository facultyRepository;
    private final UniversityMapper mapper;
    private final FacultyMapper facultyMapper;
    private final ReferenceDataClient referenceDataClient;

    private final SettlementClient settlementClient;

    private final UniversityValidator validator;

    @Override
    protected UniversityRepository getRepository() {
        return repository;
    }

    @Override
    protected BaseObjectMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator getValidator() {
        return validator;
    }

    @Override
    public List<UniversityDTO> searchRecords(UniversityFilterDTO filter) {
        return mapper.toDtoList(repository.searchRecords(filter));
    }

    @Override
    public int getRecordsCount(UniversityFilterDTO filter) {
        return repository.getRecordsCount(filter);
    }

    @Override
    protected void beforeCreateOrUpdate(UniversityDTO dto) {
        dto.setCountry(dto.getAddress().getCountry());
        dto.getAddress().setAddressType(referenceDataClient.selectById(ReferenceDataDomain.ADDRESS_TYPE.domain(), AddressType.UNIVERSITY.code()));
        if (dto.getAddress().getCountry().getId().equals(DefaultValue.BG_COUNTRY_CODE)) {
            dto.getAddress().setCity(null);
            dto.getAddress().setSettlement(settlementClient.selectById(dto.getAddress().getSettlement().getId()));
        } else {
            dto.getAddress().setSettlement(null);
        }
    }

    @Override
    public List<UniversityDTO> selectByCountry(String countryCode) {
        List<Object[]> nomenclatureDTOS = repository.selectByCountryCode(countryCode);
        return nomenclatureDTOS.stream().map(x -> {
            UniversityDTO res = new UniversityDTO();
            res.setId((Integer) x[0]);
            res.setBgName((String) x[1]);
            res.setOrgName((String) x[2]);
            res.setIsActive((Integer) x[3] != 0);
            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public void toggleActivation(Integer id) {
        UniversityEntity universityEntity = repository.findById(id).orElse(null);
        if (Objects.isNull(universityEntity)) {
            throw new RuntimeException("Cannot find university with ID = " + id);
        }

        Integer isActive = universityEntity.getActive();
        if (Objects.isNull(isActive)) {
            isActive = 0;
        }

        universityEntity.setActive(isActive == 1 ? 0 : 1);
        repository.save(universityEntity);
    }

    @Override
    public List<Integer> selectBaseUniIdByTrainingCourse(Integer id) {
        return repository.selectBaseUniIdByTrainingCourse(id);
    }

    @Override
    public List<Integer> selectSecondaryUniIdsByTrainingCourse(Integer id) {
        return repository.selectSecondaryUniIdsByTrainingCourse(id);
    }

    @Override
    public List<FacultyDTO> searchUniFacultiesByName(Integer universityId, String name, Boolean onlyActive, Integer page, Integer pageSize) {
        Pageable pageable;
        List<Integer> activeVals = onlyActive ? Arrays.asList(1) : Arrays.asList(0, 1);
        if (page != null && pageSize != null) {
            pageable = PageRequest.of(page, pageSize, Sort.by("f.name"));
        } else {
            pageable = Pageable.unpaged();
        }
        List<FacultyEntity> facultyEntities = repository.selectFacultiesByUniIdAndName(universityId, "%" + name.toLowerCase() + "%", activeVals, pageable);
        return facultyMapper.toDtoList(facultyEntities);
    }

    @Override
    public List<FacultyDTO> selectUniversityFacultiesByUniversityId(Integer universityId) {
        return facultyMapper.toDtoList(repository.selectUniversityFacultiesByUniversityId(universityId));
    }

    @Override
    public String selectNameById(Integer id) {
        return repository.selectUniversityNameById(id);
    }

    @Override
    public List<UniversityDTO> selectUniversityByBgNameExact(String bgName) {
        if (!StringUtils.hasText(bgName)) {
            return null;
        }

        List<UniversityEntity> universityEntities = repository.selectUniversityByBgNameExact(bgName);
        if (CollectionUtils.isEmpty(universityEntities)) {
            return null;
        }

        return mapper.toDtoList(universityEntities);
    }

    public UniversityDTO selectByIdWithFacultyData(Integer objectId) {
        UniversityDTO university = selectById(objectId);
        List<FacultyDTO> faculties = university.getFaculties();

        if (!CollectionUtils.isEmpty(faculties)) {
            List<Integer> identifiers = faculties.stream().map(FacultyDTO::getId).filter(Objects::nonNull).toList();
            List<UniversityFacultyRepository.FacultyUsageCount> usageCountForFaculty = facultyRepository.getUsageCountForFaculties(identifiers);
            if (!CollectionUtils.isEmpty(usageCountForFaculty)) {
                Map<Integer, Integer> map = usageCountForFaculty.stream().collect(Collectors.toMap(UniversityFacultyRepository.FacultyUsageCount::getFacultyId, UniversityFacultyRepository.FacultyUsageCount::getUsageCount));
                for (FacultyDTO f : faculties) {
                    Integer count = map.get(f.getId());
                    f.setUsageCount(Objects.isNull(count) ? 0 : count);
                }
                university.setFaculties(faculties);
            }
        }
        return university;
    }
}
