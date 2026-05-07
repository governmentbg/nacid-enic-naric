package bg.duosoft.nacid.backoffice.core.be.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class PersonToApplicantDiplomaNamesMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "civilId", source = "civilId")
    @Mapping(target = "civilIdType", source = "civilIdType")
    @Mapping(target = "foreignIdentifierCountry", source = "foreignIdentifierCountry")
    @Mapping(target = "foreignIdentifierType", source = "foreignIdentifierType")
    public abstract ApplicantDiplomaNamesDTO toApplicantDiplomaNames(PersonDTO dto);

}
