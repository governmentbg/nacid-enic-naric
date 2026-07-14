package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.diploma;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.DiplomaDTO;
import org.mapstruct.*;



@Mapper(componentModel = "spring")
public abstract class FoEducationDiplomaMapper {

    @Mapping(target = "diplomaDate", source = "date")
    @Mapping(target = "diplomaNumber", source = "number")
    @Mapping(target = "diplomaRegistrationNumber", source = "registrationNumber")
    @Mapping(target = "diplomaSeries", source = "series")
    public abstract void overrideDiplomaData(DiplomaDTO source, @MappingTarget TrainingCourseDTO target);

}
