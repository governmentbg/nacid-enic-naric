package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingLocationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingLocationEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, CountryMapper.class, TrainingInstitutionMapper.class})
public abstract class TrainingLocationMapper extends BaseObjectMapper<TrainingLocationEntity, TrainingLocationDTO> {
}
