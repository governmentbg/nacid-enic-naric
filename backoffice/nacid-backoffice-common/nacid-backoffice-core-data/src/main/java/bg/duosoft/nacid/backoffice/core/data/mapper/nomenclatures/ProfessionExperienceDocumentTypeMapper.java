package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfessionExperienceDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfessionExperienceDocumentTypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ProfessionExperienceDocumentTypeMapper extends BaseNomenclatureMapper<ProfessionExperienceDocumentTypeEntity, ProfessionExperienceDocumentTypeDTO> {
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "isForExperienceCalculation", source = "forExperienceCalculationFlag")
    public abstract ProfessionExperienceDocumentTypeDTO toDto(ProfessionExperienceDocumentTypeEntity e);
}
