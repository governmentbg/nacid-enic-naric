package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondaryProfessionalQualificationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, SecondaryProfessionGroupMapper.class})
public abstract class SecondaryProfessionalQualificationMapper extends BaseNomenclatureMapper<SecondaryProfessionalQualificationEntity, SecondaryProfessionalQualificationDTO> {
}
