package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgDocTypeRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CfgDocTypeService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgDocTypeEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CfgDocTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 16:18
 */
@Service
@RequiredArgsConstructor
public class CfgDocTypeServiceImpl implements CfgDocTypeService {

    private final CfgDocTypeRepository cfgDocTypeRepository;
    private final CfgDocTypeMapper cfgDocTypeMapper;
    private final ApplicationTypeMapper applicationTypeMapper;
    private final ApplicationSubtypeMapper applicationSubtypeMapper;

    @Override
    public List<CfgDocTypeDTO> getDocTypesConfigsByAppTypeAndSubtype(ApplicationType applicationType, ApplicationSubtype applicationSubtype) {
        List<CfgDocTypeEntity> cfgs;
        if (Objects.nonNull(applicationType) && Objects.nonNull(applicationSubtype) && (applicationSubtype.isAdditionalDocuments() || applicationSubtype.isDuplicate())) {
            cfgs = cfgDocTypeRepository.getCfgDocTypesForAppType(applicationTypeMapper.toEntity(applicationType));
        } else {
            cfgs = cfgDocTypeRepository.getCfgDocTypesForAppTypeAndSubtype(applicationTypeMapper.toEntity(applicationType), applicationSubtypeMapper.toEntity(applicationSubtype));
        }
        return cfgDocTypeMapper.toDtoList(cfgs);
    }
}
