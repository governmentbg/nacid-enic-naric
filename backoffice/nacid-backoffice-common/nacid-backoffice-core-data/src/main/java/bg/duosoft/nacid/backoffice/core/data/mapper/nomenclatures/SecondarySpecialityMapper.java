package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondarySpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, SecondaryProfessionalQualificationMapper.class, ReferenceDataMapper.class})
public abstract class SecondarySpecialityMapper extends BaseNomenclatureMapper<SecondarySpecialityEntity, SecondarySpecialityDTO> {

    @AfterMapping
    protected void afterMapping(SecondarySpecialityDTO source, @MappingTarget SecondarySpecialityEntity target) {
        if (Objects.nonNull(target.getQualificationDegree())) {
            target.getQualificationDegree().getPk().setDomain(ReferenceDataDomain.QUALIFICATION_DEGREE.domain());
        }
    }
}
