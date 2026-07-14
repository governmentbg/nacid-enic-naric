package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.prev_university;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.education.PreviousUniversityDiplomaDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {FoReferenceDataMapper.class})
public abstract class FoEducationPreviousUniversityMapper {

    @Mapping(target = "prevDiplomaUniversity.bgName", source = "universityName")
    @Mapping(target = "prevDiplomaUniversity.id", source = "universityNameId")
    @Mapping(target = "prevDiplomaNotes", source = "notes")
    @Mapping(target = "prevDiplomaSpeciality", source = "speciality")
    @Mapping(target = "prevDiplomaEduLevel", source = "gainedLevel")
    @Mapping(target = "prevDiplomaGraduationDate", expression = "java(bg.duosoft.nacidshareddata.util.date.DateUtils.convertYearToLocalDate(source.getGraduationYear()))")
    @BeanMapping(ignoreByDefault = true)
    public abstract TrainingCourseDTO overridePreviousUniversityData(PreviousUniversityDiplomaDTO source, @MappingTarget TrainingCourseDTO target);
}
