package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageConfigEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationConfigDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:49
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class LanguageMapper extends BaseNomenclatureMapper<LanguageEntity, LanguageDTO>{

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "applicationConfigs", source = "configs")
    public abstract LanguageDTO toDto(LanguageEntity entity);

    public ApplicationConfigDTO toApplicationConfig(LanguageConfigEntity configEntity){
        ApplicationType appType = ApplicationType.fromCode(configEntity.getApplicationTypeCode());
        ApplicationSubtype appSubtype = ApplicationSubtype.fromCode(configEntity.getApplicationSubtypeCode());

        ApplicationConfigDTO appConfig = new ApplicationConfigDTO(appType, appSubtype);
        return appConfig;
    }

    public abstract List<ApplicationConfigDTO> toApplicationConfigs(List<LanguageConfigEntity> configEntityList);
}
