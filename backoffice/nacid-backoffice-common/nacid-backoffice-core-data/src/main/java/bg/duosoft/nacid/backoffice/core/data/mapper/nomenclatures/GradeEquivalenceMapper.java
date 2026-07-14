package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GradeEquivalenceEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GradingScaleEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradeEquivalenceDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 28.08.2025
 * Time: 16:27
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class GradeEquivalenceMapper extends BaseNomenclatureMapper<GradeEquivalenceEntity, GradeEquivalenceDTO>{
}
