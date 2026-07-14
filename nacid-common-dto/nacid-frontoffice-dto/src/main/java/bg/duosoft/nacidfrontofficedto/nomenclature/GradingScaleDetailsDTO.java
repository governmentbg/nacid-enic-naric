package bg.duosoft.nacidfrontofficedto.nomenclature;

import lombok.Data;

@Data
public class GradingScaleDetailsDTO {
    private Integer id;
    private String symbolValues;
    private Double minValue;
    private Double maxValue;
    private String description;
    private Integer index;
    private Integer gradingScaleId;
    private GradeEquivalenceDTO gradeEquivalence;
    private GradingScaleDTO gradingScale;
}
