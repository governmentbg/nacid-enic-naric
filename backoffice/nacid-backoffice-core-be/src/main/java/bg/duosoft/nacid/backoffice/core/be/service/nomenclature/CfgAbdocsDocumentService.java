package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgAbdocsDocumentRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgAbdocsDocumentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsDocumentDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgAbdocsDocumentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CfgAbdocsDocumentService {

    private final CfgAbdocsDocumentRepository cfgAbdocsDocumentRepository;
    private final CfgAbdocsDocumentMapper cfgAbdocsDocumentMapper;


    public CfgAbdocsDocumentDTO selectByAppTypeAndSubType(String appType, String appSubType) {
        if (!StringUtils.hasText(appType) || !StringUtils.hasText(appSubType)) {
            return null;
        }

        String id = appType + appSubType;
        return selectById(id);
    }

    public CfgAbdocsDocumentDTO selectByAppType(String appType) {
        if (!StringUtils.hasText(appType)) {
            return null;
        }
        return selectById(appType);
    }

    public CfgAbdocsDocumentDTO selectById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }

        CfgAbdocsDocumentEntity entity = cfgAbdocsDocumentRepository.findById(id).orElse(null);
        return cfgAbdocsDocumentMapper.toDto(entity);
    }

}
