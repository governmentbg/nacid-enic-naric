package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.school;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HighSchoolDiplomaDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class FoEducationSchoolMapper {

    @Mapping(target = "schoolCity", source = "city")
    @Mapping(target = "schoolCountry", source = "country")
    @Mapping(target = "schoolName", source = "school")
    @Mapping(target = "schoolNotes", source = "notes")
    @Mapping(target = "schoolGraduationDate", expression = "java(bg.duosoft.nacidshareddata.util.date.DateUtils.convertYearToLocalDate(source.getGraduationYear()))")
    @BeanMapping(ignoreByDefault = true)
    public abstract TrainingCourseDTO overrideSchoolData(HighSchoolDiplomaDTO source, @MappingTarget TrainingCourseDTO target);
}
