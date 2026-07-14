package bg.duosoft.nacidcoredata.mapper.nomenclature;


import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDetailsDTO;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GradingScaleDetailsEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        GradeEquivalenceMapper.class, GradingScaleMapper.class,
})
public abstract class GradingScaleDetailsMapper extends BaseObjectMapper<GradingScaleDetailsEntity, GradingScaleDetailsDTO> {
}
