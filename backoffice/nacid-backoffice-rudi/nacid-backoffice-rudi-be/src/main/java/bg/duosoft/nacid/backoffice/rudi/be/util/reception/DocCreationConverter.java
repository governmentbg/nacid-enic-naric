package bg.duosoft.nacid.backoffice.rudi.be.util.reception;

import bg.duosoft.nacid.backoffice.abdocs.domain.DocCreation;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocRegistrationType;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocSourceType;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgabdocsdocument.AdminCfgAbdocsDocumentClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.externalnomenclaturesmap.AdminExternalNomenclaturesMapClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureSystem;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservObject;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsDocumentDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacidbackofficeshareddata.exception.AbdocsDocumentConfigException;
import bg.duosoft.nacidbackofficeshareddata.exception.DocCreationConverterException;
import bg.duosoft.nacidbackofficeshareddata.utils.reception.DocCreationConverterBase;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocCreationConverter extends DocCreationConverterBase {
    private final AdminCfgAbdocsDocumentClient adminCfgAbdocsDocumentClient;
    private final AdminExternalNomenclaturesMapClient adminExternalNomenclaturesMapClient;

    public DocCreation convertObjectForMissingDoc(ApplicationDTO application) {
        try {
            DocCreation docCreation = convertObject(application, DocSourceType.Counter, adminExternalNomenclaturesMapClient.getBySystemAndNomenclatureType(ExternalNomenclatureSystem.ABDOCS.code(), ExternalNomenclatureType.ABDOCS_SETTLEMENT.code()));
            docCreation.getRegistration().setDocRegistrationType(DocRegistrationType.ExternalRegistrationNumber);
            docCreation.getRegistration().setRegistrationNumber(application.getEntryNumber());
            docCreation.getRegistration().setRegDate(Date.from(application.getEntryDate().atStartOfDay(ZoneId.systemDefault()).plusHours(3).toInstant()));
            docCreation.getRegistration().setIncludeDateInNumber(true);
            return docCreation;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DocCreationConverterException(e.getMessage(), e);
        }
    }

    @Override
    public CfgAbdocsDocumentDTO selectAbdocsDocumentConfig(ApplicationDTO applicationDTO) {
        String appType = applicationDTO.getApplicationType().getId();
        String appSubType = applicationDTO.getApplicationSubtype().getId();
        CfgAbdocsDocumentDTO abdocsConfig = adminCfgAbdocsDocumentClient.getByAppTypeAndAppSubType(appType, appSubType);
        if (Objects.isNull(abdocsConfig)) {
            throw new AbdocsDocumentConfigException("[CFG_ABDOCS_DOCUMENT] There isn't record in cfg_abdocs_document table for appType = " + appType + " appSubType = " + appSubType);
        }
        if (Objects.isNull(abdocsConfig.getDocTypeId())) {
            throw new AbdocsDocumentConfigException("[CFG_ABDOCS_DOCUMENT] Doc type id is empty! ID: " + abdocsConfig.getId());
        }
        if (Objects.isNull(abdocsConfig.getDocRegistrationTypeId())) {
            throw new AbdocsDocumentConfigException("[CFG_ABDOCS_DOCUMENT] Doc registration type id is empty! ID: " + abdocsConfig.getId());
        }
        if (!StringUtils.hasText(abdocsConfig.getDocSubject())) {
            throw new AbdocsDocumentConfigException("[CFG_ABDOCS_DOCUMENT] Doc subject is empty! ID: " + abdocsConfig.getId());
        }
        return abdocsConfig;
    }

}
