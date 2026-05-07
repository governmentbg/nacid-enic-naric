package bg.duosoft.nacidservicesbe.mapper.common.education;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.wrapper.GraduationWayWrapperDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseGraduationWayEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshareddata.util.ReferenceDataConstants;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.11.2022
 * Time: 16:06
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class GraduationWayMapper extends BaseObjectMapper<RudiTrainingCourseGraduationWayEntity, ReferenceDataDTO> {


    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "graduationWay", source = ".")
    @Mapping(target = "id", ignore = true)
    public abstract RudiTrainingCourseGraduationWayEntity toEntity(ReferenceDataDTO referenceDataDTO);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", source = "graduationWay.pk.id")
    @Mapping(target = "domain", source = "graduationWay.pk.domain")
    @Mapping(target = "domainName", source = "graduationWay.referenceDataDomain.name")
    @Mapping(target = "name", source = "graduationWay.name")
    @Mapping(target = "index", source = "graduationWay.index")
    @Mapping(target = "isActive", source = "graduationWay.active")
    public abstract ReferenceDataDTO toDto(RudiTrainingCourseGraduationWayEntity rudiTrainingCourseGraduationWayEntity);


    public List<RudiTrainingCourseGraduationWayEntity> toEntityListFromWrapper(GraduationWayWrapperDTO wrapper){
        List<RudiTrainingCourseGraduationWayEntity> list = new ArrayList<>();
        if(wrapper != null && wrapper.getGraduationWay() != null) {
            list = toEntityList(wrapper.getGraduationWay());
            Optional<RudiTrainingCourseGraduationWayEntity> other = list.stream().filter(gw -> gw.getGraduationWay().getPk().getId().equals(ReferenceDataConstants.OTHER_VALUE)).findFirst();
            if(other.isPresent()){
                other.get().setNotes(wrapper.getGraduationWayOtherDetails());
            }
        }
        return list;
    }

    public GraduationWayWrapperDTO toDtoWrapperFromList(List<RudiTrainingCourseGraduationWayEntity> entityList){
        List<ReferenceDataDTO> list = toDtoList(entityList);
        Optional<RudiTrainingCourseGraduationWayEntity> other = entityList.stream().filter(gw -> gw.getGraduationWay().getPk().getId().equals(ReferenceDataConstants.OTHER_VALUE)).findFirst();
        GraduationWayWrapperDTO wrapper = new GraduationWayWrapperDTO(list, other.isPresent()? other.get().getNotes(): null);
        return wrapper;
    }
}
