package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.enums.ScaleTypeEnum;
import lombok.Data;

@Data
public class GradingScaleDto {
    private Integer id;
    private String scaleName;
    private ScaleTypeEnum scaleType;
    private String description;
    private String countryCode;
}
