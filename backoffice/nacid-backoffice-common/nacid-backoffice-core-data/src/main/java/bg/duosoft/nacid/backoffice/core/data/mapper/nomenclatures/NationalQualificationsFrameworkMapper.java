package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NationalQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.NationalQualificationFrameworkDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, CountryMapper.class})
public abstract class NationalQualificationsFrameworkMapper extends BaseNomenclatureMapper<NationalQualificationsFrameworkEntity, NationalQualificationFrameworkDTO> {
}
