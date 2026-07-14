package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUserDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SEApplicationsDTO {
    private Integer id;
    private String ateCode;
    private String aseCode;
    private String entryNum;
    private LocalDate entryDate;
    private String efilingId;
    private String externalSystemId;
    private String applicantName;
    private String representativeName;
    private String schoolName;
    private String schoolGradingScaleCountryCode;
    private String schoolGradingScaleCountryName;
    private ApplicationResponsibleUserDataDTO responsibleUserData;
    private String apnStatusName;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String docflowStatusName;
    private String schoolCountryCode;
    private String schoolCountryName;
    private LocalDate executionPeriodEnd;
    private LocalDate backofficeDate;
}
