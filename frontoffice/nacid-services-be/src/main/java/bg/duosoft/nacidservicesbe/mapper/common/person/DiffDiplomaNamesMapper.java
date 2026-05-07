package bg.duosoft.nacidservicesbe.mapper.common.person;

import bg.duosoft.nacidfrontofficedto.person.NaturalPersonNamesDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicantDiplomaNamesEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 24.10.2022
 * Time: 18:29
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class DiffDiplomaNamesMapper extends BaseObjectMapper<ApplicantDiplomaNamesEntity, NaturalPersonNamesDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "secondName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    public abstract ApplicantDiplomaNamesEntity toEntity(NaturalPersonNamesDTO naturalPersonNamesDTO);

    public abstract NaturalPersonNamesDTO toDto(ApplicantDiplomaNamesEntity applicantDiplomaNamesEntity);

}
