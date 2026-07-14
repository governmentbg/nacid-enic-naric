package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DiplomaDetailsDto {
    private String countryCode;
    private String year;
    private Integer scalaId;
    private List<DiplomaSubjectDto> subjects;
}


