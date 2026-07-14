package bg.duosoft.nacid.backoffice.core.data.util.abdocs;

import bg.duosoft.nacid.backoffice.abdocs.domain.DocActionExpectedResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAbdocsConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
public class AbdocsConfigUtils {

    public static boolean shouldTransferFile(DocumentTypeDTO docTypeDTO, String applicationType, String applicationSubtype) {
        if (Objects.isNull(docTypeDTO) || Objects.isNull(applicationType) || Objects.isNull(applicationSubtype)) {
            return false;
        }
        DocumentTypeAbdocsConfigDTO abdocsConfig = docTypeDTO.getSingleAbdocsConfigOrThrowException(applicationType, applicationSubtype);
        Boolean abdocsAutoInsertFlag = abdocsConfig == null ? null : abdocsConfig.getAbdocsAutoInsertFlag();
        if (Objects.isNull(abdocsAutoInsertFlag)) {
            return false;
        }

        return abdocsAutoInsertFlag;
    }

    public static boolean shouldCreateDocAction(DocumentTypeDTO docTypeDTO, String applicationType, String applicationSubtype) {
        if (Objects.isNull(docTypeDTO) || Objects.isNull(applicationType) || Objects.isNull(applicationSubtype)) {
            return false;
        }
        DocumentTypeAbdocsConfigDTO abdocsConfig = docTypeDTO.getSingleAbdocsConfigOrThrowException(applicationType, applicationSubtype);
        String taskResult = abdocsConfig.getAbdocsTaskResult();
        if (!StringUtils.hasText(taskResult)) {
            return false;
        }

        try {
            DocActionExpectedResult.valueOf(taskResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }

        return StringUtils.hasText(abdocsConfig.getAbdocsTaskUser());
    }

}
