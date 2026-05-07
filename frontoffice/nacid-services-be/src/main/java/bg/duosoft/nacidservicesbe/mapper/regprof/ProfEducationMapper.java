package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidcoredata.mapper.nomenclature.GraduationDocTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationEntryDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofSecondaryTrainingCourseEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 13:39
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        GraduationDocTypeMapper.class,
})
public abstract class ProfEducationMapper extends BaseObjectMapper<RegprofSecondaryTrainingCourseEntity, RegprofEducationEntryDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "professionalInstitution", source = "newEducationInstitutionName")
    @Mapping(target = "professionalInstitutionId", source = "newEducationInstitutionId")
    @Mapping(target = "professionalInstitutionFormerName", source = "oldEducationInstitutionName")
    @Mapping(target = "professionalInstitutionFormerNameId", source = "oldEducationInstitutionId")
    @Mapping(target = "graduationDocType", source = "documentKind")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "documentDate", source = "documentDate")
    @Mapping(target = "documentSeries", source = "documentSeries")
    @Mapping(target = "documentRegNumber", source = "documentRegistrationNumber")
    @Mapping(target = "professionalQualification", source = "professionalQualification")
    @Mapping(target = "professionalQualificationId", source = "professionalQualificationId")
    @Mapping(target = "qualificationRank", source = "qualificationRank")
    public abstract RegprofSecondaryTrainingCourseEntity toEntity(RegprofEducationEntryDTO regprofEducationEntryDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract RegprofEducationEntryDTO toDto(RegprofSecondaryTrainingCourseEntity regprofSecondaryTrainingCourseEntity);

}
