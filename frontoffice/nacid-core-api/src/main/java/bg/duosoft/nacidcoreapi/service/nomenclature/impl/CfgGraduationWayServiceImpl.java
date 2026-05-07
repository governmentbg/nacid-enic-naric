package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgGraduationWayRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CfgGraduationWayService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgGraduationWayEntity;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CfgGraduationWayMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgGraduationWayDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 14:04
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CfgGraduationWayServiceImpl implements CfgGraduationWayService {

    private final CfgGraduationWayRepository cfgGraduationWayRepository;
    private final CfgGraduationWayMapper cfgGraduationWayMapper;

    public List<CfgGraduationWayDTO> getGraduationWaysConfigs(){
        List<CfgGraduationWayEntity> graduationWayCfgEntities = cfgGraduationWayRepository.findAllByOrderByGraduationWay_IndexAsc();
        return cfgGraduationWayMapper.toDtoList(graduationWayCfgEntities);
    }
}
