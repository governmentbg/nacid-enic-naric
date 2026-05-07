package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CommissionMemberPositionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReferenceDataMapper.class})
public abstract class CommissionMemberPositionMapper extends BaseNomenclatureMapper<CommissionMemberPositionEntity, CommissionMemberPositionDTO> {
}
