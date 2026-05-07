package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicantDiplomaNamesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CivilIdTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        CivilIdTypeMapper.class,
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class,
})
public abstract class ApplicantDiplomaNamesMapper extends BaseObjectMapper<ApplicantDiplomaNamesEntity, ApplicantDiplomaNamesDTO> {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "secondName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "civilId", source = "civilId")
    @Mapping(target = "civilIdType", source = "civilIdType")
    @Mapping(target = "foreignIdentifierType", source = "foreignIdentifierType")
    @Mapping(target = "foreignIdentifierCountry", source = "foreignIdentifierCountry")
    public abstract ApplicantDiplomaNamesDTO toDto(ApplicantDiplomaNamesEntity e);

    @InheritInverseConfiguration
    public abstract ApplicantDiplomaNamesEntity toEntity(ApplicantDiplomaNamesDTO dto);

    @BeforeMapping
    protected void beforeToEntity(ApplicantDiplomaNamesDTO source, @MappingTarget ApplicantDiplomaNamesEntity target) {
        this.overrideDtoData(source);
    }

    public void overrideDtoData(ApplicantDiplomaNamesDTO dto) {
        if (Objects.nonNull(dto)) {
            ReferenceDataUtils.setDefaultDomain(dto.getForeignIdentifierType(), ReferenceDataDomain.FOREIGN_IDENTIFIER_TYPE);
        }
    }

}
