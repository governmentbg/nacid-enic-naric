package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.AddressMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.BaseNomenclatureMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, AddressMapper.class, CountryMapper.class, UniversityMapper.class})
public abstract class TrainingInstitutionMapper extends BaseNomenclatureMapper<TrainingInstitutionEntity, TrainingInstitutionDTO> {
}
