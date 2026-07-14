package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsUrlBuilder;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SummaryUtils {

    public static String getApplicantName(ApplicationDTO application) {
        if (Objects.nonNull(application)) {
            PersonDTO applicant = application.getApplicant();
            if (Objects.nonNull(applicant)) {
                return PersonUtils.getPersonName(applicant);
            }
        }
        return null;
    }

    public static String getStatusName(ApplicationDTO application) {
        if (Objects.nonNull(application)) {
            ReferenceDataDTO status = application.getStatus();
            if (Objects.nonNull(status)) {
                return status.getName();
            }
        }
        return null;
    }

    public static String getDocflowStatusName(ApplicationDTO application) {
        if (Objects.nonNull(application)) {
            ReferenceDataDTO docflowStatus = application.getDocflowStatus();
            if (Objects.nonNull(docflowStatus)) {
                return docflowStatus.getName();
            }
        }
        return null;
    }

    public static String getResponsibleUser(ApplicationDTO application) {
        if (Objects.nonNull(application)) {
            List<ApplicationResponsibleUsersDTO> responsibleUsers = application.getResponsibleUsers();
            if (!CollectionUtils.isEmpty(responsibleUsers)) {
                ApplicationResponsibleUsersDTO currentUser = responsibleUsers.stream().filter(ApplicationResponsibleUsersDTO::isLast).findFirst().orElse(null);
                if (Objects.nonNull(currentUser)) {
                    return currentUser.getFullName();
                }
            }
        }
        return null;
    }

    public static String getDocflowDocumentUrl(ApplicationDTO application, AbdocsUrlBuilder abdocsUrlBuilder) {
        if (Objects.nonNull(application)) {
            String entryNumber = application.getEntryNumber();
            LocalDate entryDate = application.getEntryDate();
            if (StringUtils.hasText(entryNumber) && Objects.nonNull(entryDate)) {
                return abdocsUrlBuilder.viewDocWithAuth(AbdocsNumbersUtils.buildRegistrationNumber(entryNumber, entryDate));
            }
        }
        return null;
    }
}
