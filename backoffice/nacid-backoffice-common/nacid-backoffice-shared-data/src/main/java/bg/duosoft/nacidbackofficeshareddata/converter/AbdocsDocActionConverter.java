package bg.duosoft.nacidbackofficeshareddata.converter;

import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAbdocsConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class AbdocsDocActionConverter {

    private final AbdocsAdminService abdocsAdminService;

    public DocActionRequest createDocActionRequest(Integer abdocsDocumentId, DocumentTypeDTO documentType, String applicationType, String applicationSubtype) {
        try {
            DocumentTypeAbdocsConfigDTO abdocsConfig = documentType.getSingleAbdocsConfigOrThrowException(applicationType, applicationSubtype);
            DocActionExpectedResult executionTargeting = DocActionExpectedResult.valueOf(abdocsConfig.getAbdocsTaskResult());

            Integer userFrom = abdocsAdminService.selectAbdocsUserIdByUsername(SecurityUtils.getUsername());
            if (Objects.isNull(userFrom)) {
                throw new RuntimeException("[Abdocs Document Action] Cannot create doc action object, because 'userFrom' is empty !");
            }

            Integer userTo = abdocsAdminService.selectAbdocsUserIdByUsername(abdocsConfig.getAbdocsTaskUser());
            if (Objects.isNull(userTo)) {
                throw new RuntimeException("[Abdocs Document Action] Cannot create doc action object, because 'userTo' is empty !");
            }

            Date dateCreated = new Date();

            DocActionRequest request = new DocActionRequest();
            request.setDocId(abdocsDocumentId);
            request.setType(executionTargeting.docActionTypes().get(0).value());
            request.setAddRootDocPermissions(true);
            request.setCreateDate(dateCreated);
            request.setUnitId(userFrom);
            request.setFromUnitId(userFrom);
            request.setExpectedResultId(executionTargeting.value());
//          request.setNote("");

            DocUnit docUnit = new DocUnit();
            docUnit.setCreateDate(dateCreated);
            docUnit.setUnitId(userTo);
            docUnit.setDocUnitRole(DocUnitRole.To.value());
            request.setDocUnits(Collections.singletonList(docUnit));

            return request;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
