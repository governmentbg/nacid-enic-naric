package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgEduLevelRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CfgEduLevelService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgEduLevelEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CfgEduLevelMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgEduLevelDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 16:09
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CfgEduLevelServiceImpl implements CfgEduLevelService {

    private final CfgEduLevelRepository cfgEduLevelRepository;
    private final CfgEduLevelMapper cfgEduLevelMapper;
    private final ApplicationTypeMapper applicationTypeMapper;
    private final ApplicationSubtypeMapper applicationSubtypeMapper;

    public List<CfgEduLevelDTO> getEduLevelsConfigs(){
        List<CfgEduLevelEntity> eduLevelCfgEntityList = cfgEduLevelRepository.findAllByOrderByEduLevel_IndexAsc();
        return cfgEduLevelMapper.toDtoList(eduLevelCfgEntityList);
    }

    @Override
    public List<CfgEduLevelDTO> getEduLevelsConfigsByApplicationTypeSubtype(ApplicationType applicationType, ApplicationSubtype applicationSubtype) {
        List<CfgEduLevelEntity> eduLevelCfgEntityList = cfgEduLevelRepository
                .findAllById_ApplicationTypeCodeAndId_ApplicationSubtypeCodeOrderByEduLevel_IndexAsc(
                    applicationTypeMapper.toEntity(applicationType),
                    applicationSubtypeMapper.toEntity(applicationSubtype)
        );
        return cfgEduLevelMapper.toDtoList(eduLevelCfgEntityList);
    }
}
