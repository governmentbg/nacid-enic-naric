package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationDocumentTypeConfigEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationDocumentTypeConfigDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReferenceDataMapper.class, CountryMapper.class})
public abstract class CfgGraduationDocumentTypeMapper extends BaseObjectMapper<CfgGraduationDocumentTypeConfigEntity, CfgGraduationDocumentTypeConfigDTO> {

    @Mapping(target = "pk.countryCode", source = "country.id")
    @Mapping(target = "pk.educationType", source = "educationType.id")
    @Mapping(target = "educationType", source = "educationType")
    @Mapping(target = "country", source = "country")
    public abstract CfgGraduationDocumentTypeConfigEntity toEntity(CfgGraduationDocumentTypeConfigDTO e);
}
