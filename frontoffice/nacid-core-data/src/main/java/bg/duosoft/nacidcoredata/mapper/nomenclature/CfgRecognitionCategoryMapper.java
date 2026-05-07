package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgRecognitionCategoryEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgRecognitionCategoryDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 13:09
 */
@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class, ApplicationTypeMapper.class, ApplicationSubtypeMapper.class})
public abstract class CfgRecognitionCategoryMapper extends BaseObjectMapper<CfgRecognitionCategoryEntity, CfgRecognitionCategoryDTO> {

    @Mapping(target = "applicationType", source = "id.applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "id.applicationSubtypeCode")
    public abstract CfgRecognitionCategoryDTO toDto(CfgRecognitionCategoryEntity entity);

    @InheritInverseConfiguration
    public abstract CfgRecognitionCategoryEntity toEntity(CfgRecognitionCategoryDTO dto);
}
