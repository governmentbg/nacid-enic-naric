package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUserDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegprofApplicationsDTO {
    private Integer id;
    private String ateCode;
    private String aseCode;
    private String entryNum;
    private LocalDate entryDate;
    private String efilingId;
    private String externalSystemId;
    private LocalDate endDate;
    private Boolean imi;
    private String applicantName;
    private String applicantCivilId;
    private ApplicationResponsibleUserDataDTO responsibleUserData;
    private String apnStatusName;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String docflowStatusName;
    private String serviceTypeId;
    private String applicationProfQualification;
    private LocalDate backofficeDate;
}
