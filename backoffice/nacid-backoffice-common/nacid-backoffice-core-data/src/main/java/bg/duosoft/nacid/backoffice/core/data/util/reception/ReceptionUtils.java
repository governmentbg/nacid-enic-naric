package bg.duosoft.nacid.backoffice.core.data.util.reception;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import org.springframework.data.util.Pair;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ReceptionUtils {

    public static void setApplicationPredefinedData(ApplicationDTO application, Doc doc, String status, String docflowStatus, boolean isFoAppAccept) {
        application.setRowVersion(DefaultValue.INITIAL_ROW_NUMBER);
        application.setStatus(new ReferenceDataDTO(ReferenceDataDomain.APPLICATION_STATUS.domain(), status));
        application.setDocflowStatus(new ReferenceDataDTO(ReferenceDataDomain.DOCFLOW_STATUS.domain(), docflowStatus));
        application.setDateCreated(LocalDateTime.now());
        application.setUserCreated(SecurityUtils.getUsername());

        if (!isFoAppAccept) {
            String regNumber = doc.getRegUri();
            if (!StringUtils.hasText(regNumber)) {
                throw new RuntimeException("Entry num is empty! Abdocs document id =  " + doc.getDocId());
            }

            LocalDate regDate = DateUtils.convertToLocalDate(doc.getRegDate());
            if (Objects.isNull(regDate)) {
                throw new RuntimeException("Entry date is empty! Abdocs document id: " + doc.getDocId());
            }

            Pair<String, LocalDate> pair = AbdocsNumbersUtils.extractEntryNumberAndDate(regNumber);
            application.setEntryNumber(pair.getFirst());
            application.setEntryDate(pair.getSecond());
        }
    }
}
