package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgRecognitionCategoryRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CfgRecognitionCategoryService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgRecognitionCategoryEntity;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CfgRecognitionCategoryMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgRecognitionCategoryDTO;
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
public class CfgRecognitionCategoryServiceImpl implements CfgRecognitionCategoryService {

    private final CfgRecognitionCategoryRepository cfgRecognitionCategoryRepository;
    private final CfgRecognitionCategoryMapper cfgRecognitionCategoryMapper;

    @Override
    public List<CfgRecognitionCategoryDTO> getRecognitionCategoryConfigs(){
        List<CfgRecognitionCategoryEntity> configs = cfgRecognitionCategoryRepository.findAllByOrderByRecognitionCategory_IndexAsc();
        return cfgRecognitionCategoryMapper.toDtoList(configs);
    }
}
