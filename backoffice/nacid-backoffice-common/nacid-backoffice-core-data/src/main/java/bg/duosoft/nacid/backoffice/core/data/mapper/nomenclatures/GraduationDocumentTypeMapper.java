package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationDocumentTypeConfigEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GraduationDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, CfgGraduationDocumentTypeMapper.class})
public abstract class GraduationDocumentTypeMapper extends BaseNomenclatureMapper<GraduationDocumentTypeEntity, GraduationDocumentTypeDTO> {

    @AfterMapping
    protected void afterMapping(GraduationDocumentTypeDTO source, @MappingTarget GraduationDocumentTypeEntity target) {
        List<CfgGraduationDocumentTypeConfigEntity> configs = target.getConfigs();
        if (!CollectionUtils.isEmpty(configs)) {
            for (CfgGraduationDocumentTypeConfigEntity config : configs) {
                if (Objects.isNull(config.getEducationType()) || Objects.isNull(config.getEducationType().getPk())) {
                    throw new RuntimeException("Empty education type!");
                }
                config.getPk().setGraduationDocumentTypeId(target.getId());
                config.setGraduationDocumentType(target);
                config.getEducationType().getPk().setDomain(ReferenceDataDomain.EDUCATION_TYPE.domain());
            }
        }
    }
}
