package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberSpecialityDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberSpecialityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 17:52
 */
@Mapper(componentModel = "spring")
public abstract class ApplicationCommissionMemberSpecialityMapper extends BaseObjectMapper<ApplicationCommissionMemberSpecialityEntity, ApplicationCommissionMemberSpecialityDTO> {

}
