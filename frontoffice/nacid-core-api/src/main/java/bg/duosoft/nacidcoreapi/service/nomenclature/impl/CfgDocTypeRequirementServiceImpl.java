package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgDocTypeRequirementRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CfgDocTypeRequirementService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgDocTypeRequirementEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CfgDocTypeRequirementMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeRequirementDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:34
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CfgDocTypeRequirementServiceImpl implements CfgDocTypeRequirementService {

    private final CfgDocTypeRequirementRepository cfgDocTypeRequirementRepository;
    private final CfgDocTypeRequirementMapper cfgDocTypeRequirementMapper;
    private final ApplicationTypeMapper applicationTypeMapper;
    private final ApplicationSubtypeMapper applicationSubtypeMapper;

    public List<CfgDocTypeRequirementDTO> getDocTypeRequirementConfigs(){
        List<CfgDocTypeRequirementEntity> requirementResult = cfgDocTypeRequirementRepository.findAll();
        return cfgDocTypeRequirementMapper.toDtoList(requirementResult);
    }

    public List<CfgDocTypeRequirementDTO> getByApplicationTypeAndSubtype(ApplicationType applicationType, ApplicationSubtype applicationSubtype){
        List<CfgDocTypeRequirementEntity> requirementResult = cfgDocTypeRequirementRepository.getByApplicationTypeAndSubtype(
                applicationTypeMapper.toEntity(applicationType), applicationSubtypeMapper.toEntity(applicationSubtype));
        return cfgDocTypeRequirementMapper.toDtoList(requirementResult);
    }
}
