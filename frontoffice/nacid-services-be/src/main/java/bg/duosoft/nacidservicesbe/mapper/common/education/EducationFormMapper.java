package bg.duosoft.nacidservicesbe.mapper.common.education;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.wrapper.EducationFormWrapperDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseTrainingFormEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.10.2022
 * Time: 13:25
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class EducationFormMapper extends BaseObjectMapper<RudiTrainingCourseTrainingFormEntity, ReferenceDataDTO> {


    public List<RudiTrainingCourseTrainingFormEntity> toEntityList(ReferenceDataDTO referenceData) {
        List<RudiTrainingCourseTrainingFormEntity> trainingForms = new ArrayList<>();
        if(referenceData != null){
            trainingForms.add(toEntity(referenceData));
        }
        return trainingForms;
    }

    public ReferenceDataDTO toDtoFromList(List<RudiTrainingCourseTrainingFormEntity> entityList){
        if(entityList != null && entityList.size()>0){
            return toDto(entityList.get(0));
        }
        return null;
    }

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "trainingForm", source = ".")
    @Mapping(target = "id", ignore = true)
    public abstract RudiTrainingCourseTrainingFormEntity toEntity(ReferenceDataDTO referenceData);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", source = "trainingForm.pk.id")
    @Mapping(target = "domain", source = "trainingForm.pk.domain")
    @Mapping(target = "domainName", source = "trainingForm.referenceDataDomain.name")
    @Mapping(target = "name", source = "trainingForm.name")
    @Mapping(target = "index", source = "trainingForm.index")
    @Mapping(target = "isActive", source = "trainingForm.active")
    public abstract ReferenceDataDTO toDto(RudiTrainingCourseTrainingFormEntity entity);


    public List<RudiTrainingCourseTrainingFormEntity> toEntityListFromWrapper(EducationFormWrapperDTO wrapper){
        List<RudiTrainingCourseTrainingFormEntity> trainingForms = new ArrayList<>();
        if(wrapper != null){
            trainingForms = toEntityList(wrapper.getEducationForm());
            if(trainingForms.size() >0 ){
                trainingForms.get(0).setNotes(wrapper.getEducationFormOtherDetails());
            }
        }
        return trainingForms;
    }

    public EducationFormWrapperDTO toDtoWrapperFromList(List<RudiTrainingCourseTrainingFormEntity> entityList){
        ReferenceDataDTO referenceDataDTO = toDtoFromList(entityList);
        String notes = null;
        if(entityList != null && entityList.size()>0){
            notes = entityList.get(0).getNotes();
        }
        return new EducationFormWrapperDTO(referenceDataDTO, notes);
    }
}
