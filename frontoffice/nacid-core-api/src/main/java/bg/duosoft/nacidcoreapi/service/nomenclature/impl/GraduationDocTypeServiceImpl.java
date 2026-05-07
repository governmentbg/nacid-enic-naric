package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.GraduationDocTypeRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.GraduationDocTypeService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.GraduationDocTypeValidator;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeEntity;
import bg.duosoft.nacidcoredata.mapper.nomenclature.GraduationDocTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.nomenclature.GraduationDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 11:03
 */
@Service
@RequiredArgsConstructor
public class GraduationDocTypeServiceImpl extends NomenclatureServiceBaseImpl<Integer, GraduationDocTypeDTO, GraduationDocTypeFilterDTO> implements GraduationDocTypeService {

    private final GraduationDocTypeRepository repository;
    private final GraduationDocTypeMapper mapper;
    private final GraduationDocTypeValidator validator;

    @Override
    public List<GraduationDocTypeDTO> getByEducationType(EducationType educationType, boolean onlyActive) {
        List<GraduationDocTypeEntity> docTypes =
                onlyActive?
                repository.getAllActiveForEducationTypeCode(educationType.getCode()) :
                        repository.getAllForEducationTypeCode(educationType.getCode());
        return mapper.toDtoList(docTypes);
    }

    @Override
    protected GraduationDocTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected GraduationDocTypeMapper getMapper() {
        return mapper;
    }

    @Override
    public GraduationDocTypeValidator getValidator() {
        return validator;
    }
}
