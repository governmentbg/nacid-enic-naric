package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleInfoDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradingScaleDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class GradingScaleInfoMapper extends BaseMapper<RudiGradingScaleDetailsEntity, GradingScaleInfoDto> {

    @Mapping(target = "gradeBgEquivalence", expression = "java(buildGradeBgEquivalence(entity))")
    @Mapping(target = "gradeRange", expression = "java(buildGrade(entity))")
    public abstract GradingScaleInfoDto toDto(RudiGradingScaleDetailsEntity entity);


    protected String buildGrade(RudiGradingScaleDetailsEntity entity) {
        if (entity.getSymbolValues() != null) {
            return String.format("%s", entity.getSymbolValues());
        }
        return String.format("%s - %s", entity.getMinValue().toString(),
                entity.getMaxValue().toString());
    }

    protected String buildGradeBgEquivalence(RudiGradingScaleDetailsEntity entity) {
        return String.format("%s (%s)",
                entity.getGradeEquivalence().getBulgarianGradeText(),
                entity.getGradeEquivalence().getBulgarianGrade());
    }


}
