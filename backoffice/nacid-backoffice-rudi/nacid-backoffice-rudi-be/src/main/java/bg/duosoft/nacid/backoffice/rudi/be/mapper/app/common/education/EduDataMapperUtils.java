package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.UniversityAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.GraduationWayType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.RecognitionPurposeType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.RudiEduDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.mandatory.RudiMandatoryEduData;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

public class EduDataMapperUtils {
    public static void recognitionPurposeSectionToApplication(List<ApplicationRecognitionPurposeDTO> arps, List<ApplicationRecognitionPurposeDTO> newArps, Integer applicationId, Boolean dtoValue, String code, String notes) {
        if (dtoValue) {
            ApplicationRecognitionPurposeDTO purposeDTO = arps.stream().filter(o -> o.getRecognitionPurpose().getId().equals(code)).findFirst().orElse(null);
            if (Objects.isNull(purposeDTO)) {
                purposeDTO = new ApplicationRecognitionPurposeDTO();
                purposeDTO.setApplicationId(applicationId);
                purposeDTO.setRecognitionPurpose(new ReferenceDataDTO(ReferenceDataDomain.RECOGNITION_PURPOSE.domain(), code));
            }
            newArps.add(purposeDTO);

            if (code.equals(RecognitionPurposeType.OTHER.code())) {
                purposeDTO.setNotes(notes);
            } else {
                purposeDTO.setNotes(null);
            }
        }
    }

    public static void graduationWaySectionToTrainingCourse(List<TrainingCourseGraduationWayDTO> graduationWays, List<TrainingCourseGraduationWayDTO> newGraduationWays, Integer trainingCourseId, Boolean dtoValue, String code, String notes) {
        if (Objects.nonNull(dtoValue) && dtoValue) {
            TrainingCourseGraduationWayDTO graduationWayDTO = graduationWays.stream().filter(o -> o.getGraduationWay().getId().equals(code)).findFirst().orElse(null);
            if (Objects.isNull(graduationWayDTO)) {
                graduationWayDTO = new TrainingCourseGraduationWayDTO();
                graduationWayDTO.setTrainingCourseId(trainingCourseId);
                graduationWayDTO.setGraduationWay(new ReferenceDataDTO(ReferenceDataDomain.GRADUATION_WAY.domain(), code));
            }
            newGraduationWays.add(graduationWayDTO);

            if (code.equals(GraduationWayType.OTHER.code())) {
                graduationWayDTO.setNotes(notes);
            } else {
                graduationWayDTO.setNotes(null);
            }
        }
    }

    public static void fillPrevDiplomaUniversity(RudiApplicationDTO source, RudiEduDataBaseDTO target) {
        UniversityDTO prevDiplomaUniversity = source.getTrainingCourse().getPrevDiplomaUniversity();
        if (Objects.nonNull(prevDiplomaUniversity)) {
            target.setPrevDiplomaUniversity(UniversityAutocompleteDTO.universityBuilder()
                            .id(prevDiplomaUniversity.getId())
                            .name(prevDiplomaUniversity.getBgName())
                            .nameEn(prevDiplomaUniversity.getOrgName())
                            .country(prevDiplomaUniversity.getCountry().getName())
                            .city(StringUtils.hasText(prevDiplomaUniversity.getAddress().getCity()) ?
                            prevDiplomaUniversity.getAddress().getCity() :
                            prevDiplomaUniversity.getAddress().getSettlement().getSimpleSettlementName())
                            .isActive(prevDiplomaUniversity.getIsActive())
                            .build()
            );
        }
    }


    public static void afterOverrideMandatoryEduData(RudiMandatoryEduData source, @MappingTarget RudiApplicationDTO target) {
        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {

            if (Objects.isNull(source.getBaseUniversityId())) {
                trainingCourse.setBaseUniversity(null);
            }

        }
    }

    public static void afterToMandatoryEduDataDto(RudiApplicationDTO source, @MappingTarget RudiMandatoryEduData target) {

    }

}
