package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReferenceDataMapper.class, CfgLegalReasonToAppTypeMapper.class})
public abstract class LegalReasonMapper extends BaseNomenclatureMapper<LegalReasonEntity, LegalReasonDTO> {

    @AfterMapping
    protected void afterToEntity(LegalReasonDTO source, @MappingTarget LegalReasonEntity target) {
        if (target.getConfigs() != null) {
            target.getConfigs().forEach(c -> {
                c.setLegalReason(target);
                c.getPk().setLrnId(target.getId());
            });
        }
    }
}
