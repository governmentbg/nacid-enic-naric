package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgRecognitionCategoryToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgRecognitionCategoryToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 30.05.2023
 */
@Mapper(componentModel = "spring", uses = {ApplicationTypeMapper.class, ApplicationSubtypeMapper.class, ReferenceDataMapper.class})
public abstract class CfgRecognitionCategoryToAppTypeMapper extends BaseObjectMapper<CfgRecognitionCategoryToAppTypeEntity, CfgRecognitionCategoryToAppTypeDTO> {
    @Mapping(target = "pk.ateCode", source = "applicationType.id")
    @Mapping(target = "pk.aseCode", source = "applicationSubtype.id")
    @Mapping(target = "pk.rcyCode", source = "recognitionCategory.id")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "recognitionCategory", source = "recognitionCategory")
    public abstract CfgRecognitionCategoryToAppTypeEntity toEntity(CfgRecognitionCategoryToAppTypeDTO dto);

    @AfterMapping
    public void afterToEntity(CfgRecognitionCategoryToAppTypeDTO source, @MappingTarget CfgRecognitionCategoryToAppTypeEntity target) {
        if (Objects.nonNull(target.getRecognitionCategory())) {
            ReferenceDataUtils.setDefaultDomain(target.getRecognitionCategory(), ReferenceDataDomain.RECOGNITION_CATEGORY);
        }
    }


}
