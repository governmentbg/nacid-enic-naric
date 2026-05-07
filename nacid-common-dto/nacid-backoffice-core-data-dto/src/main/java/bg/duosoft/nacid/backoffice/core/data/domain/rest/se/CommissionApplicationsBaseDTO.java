package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommissionApplicationsBaseDTO {
    private String entryNum;
    private LocalDate entryDate;
    private Integer applicantId;
    private String applicantName;
    private String ateCode;
    private String applicationTypeName;
    private String aseCode;
    private String applicationSubtypeName;
    private String statusCode;
    private String statusName;
    private String schoolName;
    private String schoolGradingScaleCountryCode;
    private String internationalGradingSystem;
    private String schoolCountryName;
    private String gradingScaleCountryName;
    private String gradingSystemName;
    private String responsibleUser;
}
