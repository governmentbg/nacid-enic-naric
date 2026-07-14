package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EuropeanQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.EuropeanQualificationFrameworkDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class EuropeanQualificationsFrameworkMapper extends BaseNomenclatureMapper<EuropeanQualificationsFrameworkEntity, EuropeanQualificationFrameworkDTO> {
}
