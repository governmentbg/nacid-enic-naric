package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GradingScaleDetailsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDetailsDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:56
 */
@Mapper(componentModel = "spring", uses = {GradingScaleMapper.class, GradeEquivalenceMapper.class})
public abstract class GradingScaleDetailsMapper extends BaseObjectMapper<GradingScaleDetailsEntity, GradingScaleDetailsDTO> {
    public abstract GradingScaleDetailsDTO toDto(GradingScaleDetailsEntity e);
}
