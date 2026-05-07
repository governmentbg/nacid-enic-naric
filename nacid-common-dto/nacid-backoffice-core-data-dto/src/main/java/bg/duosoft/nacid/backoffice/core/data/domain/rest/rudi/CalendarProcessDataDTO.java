package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarProcessDataDTO {
    private Integer applicationId;
    private Integer calendarId;
    private String motives;
    private String applicantInfo;
    private String recognizedEduLevel;
    private String recognizedQualification;
    private Integer recognizedProfGroupId;
    private String statusCode;
    private List<String> specialities;
    private String recognizedEduLevelName;
    private String recognizedProfGroupName;
    private String statusName;
    private Integer legalReasonId;
    private String legalReasonName;
}
