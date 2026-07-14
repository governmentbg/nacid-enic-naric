package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Data;

@Data
public class GradingScaleDetailsDTO {
    private Integer id;
    private String symbolValues;
    private Double minValue;
    private Double maxValue;
    private String description;
    private Integer index;
    private GradingScaleDTO gradingScale;
    private GradeEquivalenceDTO gradeEquivalence;
}
