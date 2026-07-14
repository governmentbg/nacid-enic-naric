package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgServiceTypeRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CfgServiceTypeService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgServiceTypeEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CfgServiceTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgServiceTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 11:20
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CfgServiceTypeServiceImpl implements CfgServiceTypeService {

    private final CfgServiceTypeRepository cfgServiceTypeRepository;
    private final CfgServiceTypeMapper cfgServiceTypeMapper;
    private final ApplicationTypeMapper applicationTypeMapper;
    private final ApplicationSubtypeMapper applicationSubtypeMapper;

    public List<CfgServiceTypeDTO> getServiceTypesConfigs(){
        List<CfgServiceTypeEntity> serviceTypeEntities = cfgServiceTypeRepository.findAll();
        return cfgServiceTypeMapper.toDtoList(serviceTypeEntities);
    }

    @Override
    public List<CfgServiceTypeDTO> getServiceTypesConfigsByApplicationTypeSubtype(ApplicationType applicantType, ApplicationSubtype applicationSubtype, boolean onlyActive) {
        List<CfgServiceTypeEntity> serviceTypeEntities;
        if(onlyActive){
            serviceTypeEntities = cfgServiceTypeRepository.getOnlyActiveConfigsByApplicationTypeSubtype(applicationTypeMapper.toEntity(applicantType),
                    applicationSubtypeMapper.toEntity(applicationSubtype));
        } else {
            serviceTypeEntities = cfgServiceTypeRepository.getConfigsByApplicationTypeSubtype(applicationTypeMapper.toEntity(applicantType),
                    applicationSubtypeMapper.toEntity(applicationSubtype));
        }
        return cfgServiceTypeMapper.toDtoList(serviceTypeEntities);
    }
}
