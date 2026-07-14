package bg.duosoft.nacidbackofficeshareddata.utils.reception;

import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsDocumentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsConverter;
import bg.duosoft.nacidbackofficeshareddata.exception.DocCreationConverterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class DocCreationConverterBase {
    public DocCreation convertObject(ApplicationDTO applicationDTO, DocSourceType docSourceType, List<ExternalNomenclaturesMapDTO> abdocsSettlementsMap) {
        try {
            DocCreation docCreation = new DocCreation();
            CfgAbdocsDocumentDTO config = selectAbdocsDocumentConfig(applicationDTO);

            docCreation.setRegistration(new RegistrationDto());
            docCreation.setDocSourceTypeId(docSourceType.value());
            docCreation.getRegistration().setDocRegistrationType(DocRegistrationType.selectByValue(config.getDocRegistrationTypeId()));
            docCreation.setDocTypeId(config.getDocTypeId());
            docCreation.setDocSubject(config.getDocSubject());
            docCreation.setDocCaseLink(new DocCaseLinkDO());
            docCreation.setReceivedOriginalState(ReceivedOriginalState.ReceivedOriginal);
            docCreation.setCorrespondents(AbdocsConverter.createDocCorrespondents(applicationDTO, abdocsSettlementsMap));
            if (!ObjectUtils.isEmpty(config.getDocTo())) {
                if (docCreation.getAdditionalDocUnits() == null) {
                    docCreation.setAdditionalDocUnits(new ArrayList<>());
                }
                AdditionalDocUnit adu = new AdditionalDocUnit();
                adu.setRole(DocUnitRole.To);
                adu.setUsername(config.getDocTo());
                docCreation.getAdditionalDocUnits().add(adu);
            }
            return docCreation;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DocCreationConverterException(e.getMessage(), e);
        }
    }
    public abstract CfgAbdocsDocumentDTO selectAbdocsDocumentConfig(ApplicationDTO applicationDTO);
}
