package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.training_form;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseTrainingFormDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.wrapper.EducationFormWrapperDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FoReferenceDataMapper.class})
public abstract class FoTrainingFormMapper {
    @Mapping(target = "trainingForm", source = "educationForm")
    @Mapping(target = "notes", source = "educationFormOtherDetails")
    public abstract TrainingCourseTrainingFormDTO toTrainingForm(EducationFormWrapperDTO source);

}
