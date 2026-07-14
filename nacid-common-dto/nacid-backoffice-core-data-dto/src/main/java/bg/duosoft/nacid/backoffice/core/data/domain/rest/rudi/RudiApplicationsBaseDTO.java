package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUserDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RudiApplicationsBaseDTO {
    private String ateCode;
    private String aseCode;
    private String aseName;
    private String entryNum;
    private LocalDate entryDate;
    private String efilingId;
    private String applicantName;
    private String diplomaOwnerName;
    private String diplomaOwnerCivilId;
    private String universityName;
    private String universityCountryName;
    private Integer universityId;
    private ApplicationResponsibleUserDataDTO responsibleUserData;
    private String specialityName;
    private String apnStatusName;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String docflowStatusName;
    private String commissionSessions;
    private Integer expertsCount;
    private Integer expertsProcessedStatus;
    private String recognizedProfGroupName;
    private String recognizedQualification;
    private String recognizedEduLevelName;
    private String recognizedSpecialityName;
    private String eduLevelName;
    private String originalEduLevelTranslated;
    private String sarFlag;
    private String personalDocumentTypeName;
    private String serviceTypeId;
    private String crfCode;
    private String manualTempUniName;
    private LocalDate backofficeDate;
}
