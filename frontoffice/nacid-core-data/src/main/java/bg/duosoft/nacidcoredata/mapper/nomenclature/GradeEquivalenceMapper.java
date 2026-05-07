package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.GradeEquivalenceDTO;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GradeEquivalenceEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class GradeEquivalenceMapper extends BaseObjectMapper<GradeEquivalenceEntity, GradeEquivalenceDTO> {
}
