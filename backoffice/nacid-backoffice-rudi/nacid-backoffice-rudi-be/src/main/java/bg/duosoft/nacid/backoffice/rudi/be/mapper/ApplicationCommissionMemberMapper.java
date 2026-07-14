package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CommissionMemberPositionMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.LegalReasonMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberSpecialityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 17:52
 */
@Mapper(componentModel = "spring", uses = {ReferenceDataMapper.class, CommissionMemberMapper.class, CommissionMemberPositionMapper.class, LegalReasonMapper.class, ApplicationCommissionMemberSpecialityMapper.class, IntegerToBooleanMapper.class})
public abstract class ApplicationCommissionMemberMapper extends BaseObjectMapper<ApplicationCommissionMemberEntity, ApplicationCommissionMemberDTO> {
    @AfterMapping
    protected void afterToEntity(ApplicationCommissionMemberDTO source, @MappingTarget ApplicationCommissionMemberEntity target) {
        List<ApplicationCommissionMemberSpecialityEntity> specialities = target.getApplicationCommissionMemberSpecialities();
        if (specialities != null) {
            specialities.forEach(s -> s.setApplicationCommissionMember(target));
        }
    }

}
