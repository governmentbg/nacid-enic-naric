package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.UniversityAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.GraduationWayType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.RecognitionPurposeType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.RudiEduDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.SarUdirecEduDataCommonDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityService;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperUtils.*;

public abstract class EduDataMapperBase<D extends RudiEduDataBaseDTO> {

    @Autowired
    private UniversityService universityService;

    public abstract D toEducationDataSection(RudiApplicationDTO application);

    public abstract void overrideApplicationData(D source, @MappingTarget RudiApplicationDTO target);

    public abstract void afterOverride(RudiEduDataBaseDTO source, @MappingTarget RudiApplicationDTO target);

    public abstract void afterToEducationDataSection(RudiApplicationDTO source, @MappingTarget RudiEduDataBaseDTO target);

    public void commonAfterOverride(RudiEduDataBaseDTO source, @MappingTarget RudiApplicationDTO target) {
        afterOverrideMandatoryEduData(source, target);

        UniversityAutocompleteDTO prevDiplomaUniversity = source.getPrevDiplomaUniversity();
        if (Objects.nonNull(prevDiplomaUniversity)) {
            target.getTrainingCourse().setPrevDiplomaUniversity(new UniversityDTO());
            target.getTrainingCourse().getPrevDiplomaUniversity().setId(prevDiplomaUniversity.getId());
        }

        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {

            if (Objects.nonNull(source.getProfGroupId())) {
                trainingCourse.setProfGroup(new ProfGroupDTO(source.getProfGroupId()));
            } else {
                trainingCourse.setProfGroup(null);
            }

            if (Objects.nonNull(source.getGraduationDocumentTypeId())) {
                trainingCourse.setGraduationDocumentType(new GraduationDocumentTypeDTO(source.getGraduationDocumentTypeId()));
            } else {
                trainingCourse.setGraduationDocumentType(null);
            }

            if (Objects.isNull(source.getPrevDiplomaUniversity()) || Objects.isNull(source.getPrevDiplomaUniversity().getId())) {
                trainingCourse.setPrevDiplomaUniversity(null);
            }

            if (Objects.isNull(source.getPrevDiplomaEduLevel()) || Objects.isNull(source.getPrevDiplomaEduLevel().getId())) {
                trainingCourse.setPrevDiplomaEduLevel(null);
            } else {
                ReferenceDataUtils.setDefaultDomain(trainingCourse.getPrevDiplomaEduLevel(), ReferenceDataDomain.EDUCATION_LEVEL);
            }

            if (Objects.isNull(source.getRecognitionCategory()) || Objects.isNull(source.getRecognitionCategory().getId())) {
                trainingCourse.setRecognitionCategory(null);
            } else {
                ReferenceDataUtils.setDefaultDomain(trainingCourse.getRecognitionCategory(), ReferenceDataDomain.RECOGNITION_CATEGORY);
            }

            if (Objects.isNull(source.getDurationUnit()) || Objects.isNull(source.getDurationUnit().getId())) {
                trainingCourse.setDurationUnit(null);
            } else {
                ReferenceDataUtils.setDefaultDomain(trainingCourse.getDurationUnit(), ReferenceDataDomain.DURATION_UNIT);
            }

            TrainingCourseTrainingFormDTO trainingFormDTO = trainingCourse.getTrainingForm();
            if (Objects.nonNull(trainingFormDTO) && Objects.nonNull(trainingFormDTO.getTrainingForm()) && Objects.nonNull(trainingFormDTO.getTrainingForm().getId())) {
                ReferenceDataUtils.setDefaultDomain(trainingFormDTO.getTrainingForm(), ReferenceDataDomain.TRAINING_FORM);
            } else {
                trainingCourse.setTrainingForm(null);
            }

            trainingCourse.setTrainingCourseUniversities(new ArrayList<>());
            if (Objects.nonNull(source.getPrimaryUniversity()) && Objects.nonNull(source.getBaseUniversityId())) {
                source.getPrimaryUniversity().setOrdNum(1);
                trainingCourse.getTrainingCourseUniversities().add(source.getPrimaryUniversity());
            }
            List<TrainingCourseUniversityDTO> secondaryUniversities = source.getSecondaryUniversities().stream().filter(Objects::nonNull).toList();
            if (!CollectionUtils.isEmpty(secondaryUniversities)) {
                for (TrainingCourseUniversityDTO uni : secondaryUniversities) {
                    uni.setOrdNum(2);
                    if (!StringUtils.hasText(uni.getUniversityNameTranslated())) {
                        uni.setUniversityNameTranslated(uni.getUniversity().getBgName());
                    }
                }
                trainingCourse.getTrainingCourseUniversities().addAll(secondaryUniversities);
            }

            overrideUniversityExamsOnEduDataChange(trainingCourse);
            overrideLocationExamFieldsOnEduDataChange(trainingCourse);
            overrideDiplomaExamFieldsOnEduDataChange(trainingCourse);
        }
    }

    private void overrideDiplomaExamFieldsOnEduDataChange(TrainingCourseDTO trainingCourse) {
        TrainingCourseDiplomaExaminationDTO trainingCourseDiplomaExamination = trainingCourse.getDiplomaExamination();
        if (Objects.nonNull(trainingCourseDiplomaExamination)) {
            CompetentInstitutionDTO examinationCompetentInstitution = trainingCourseDiplomaExamination.getCompetentInstitution();
            if (Objects.nonNull(examinationCompetentInstitution)) {
                List<String> universityCountryIds = trainingCourse.getTrainingCourseUniversities().stream().map(e -> e.getUniversity().getCountry().getId()).toList();
                String competentInstituionId = examinationCompetentInstitution.getCountry().getId();

                if (!CollectionUtils.isEmpty(universityCountryIds) && !universityCountryIds.contains(competentInstituionId)) {
                    trainingCourseDiplomaExamination.setCompetentInstitution(null);
                }
            }
        }
    }

    private void overrideLocationExamFieldsOnEduDataChange(TrainingCourseDTO trainingCourse) {
        TrainingLocationExaminationDTO trainingLocationExamination = trainingCourse.getTrainingLocationExamination();
        if (Objects.nonNull(trainingLocationExamination)) {
            List<TrainingLocationDTO> trainingLocations = trainingCourse.getTrainingLocations();
            if (!CollectionUtils.isEmpty(trainingLocations)) {
                List<Integer> trainingCourseUniversityIds = trainingCourse.getTrainingCourseUniversities().stream().map(e -> e.getUniversity().getId()).toList();

                for (TrainingLocationDTO trainingLocation : trainingLocations) {
                    TrainingInstitutionDTO examinationTrainingInstitution = trainingLocation.getExaminationTrainingInstitution();
                    if (Objects.nonNull(examinationTrainingInstitution)) {
                        List<UniversityDTO> universities = examinationTrainingInstitution.getUniversities();
                        if (!CollectionUtils.isEmpty(universities)) {
                            List<Integer> examLocationUniversityIds = universities.stream().map(UniversityDTO::getId).filter(Objects::nonNull).toList();
                            if (!CollectionUtils.isEmpty(examLocationUniversityIds)) {
                                boolean isExamLocationTrainingInstitutionValid = false;
                                for (Integer tcUniId : trainingCourseUniversityIds) {
                                    boolean containsTrainingCourseUniId = examLocationUniversityIds.contains(tcUniId);
                                    if (containsTrainingCourseUniId) {
                                        isExamLocationTrainingInstitutionValid = true;
                                        break;
                                    }
                                }

                                if (!isExamLocationTrainingInstitutionValid) {
                                    trainingLocation.setExaminationTrainingInstitution(null);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void overrideUniversityExamsOnEduDataChange(TrainingCourseDTO trainingCourse) {
        List<TrainingCourseUniversityExaminationDTO> trainingCourseUniversityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();
        if (!CollectionUtils.isEmpty(trainingCourseUniversityExaminations)) {
            List<TrainingCourseUniversityExaminationDTO> filteredUniExaminations = new ArrayList<>();
            List<TrainingCourseUniversityDTO> trainingCourseUniversities = trainingCourse.getTrainingCourseUniversities();

            for (TrainingCourseUniversityExaminationDTO examination : trainingCourseUniversityExaminations) {
                TrainingCourseUniversityDTO existingExamination = trainingCourseUniversities.stream().filter(e -> examination.getUniversity().getId().equals(e.getUniversity().getId())).findFirst().orElse(null);
                if (Objects.nonNull(existingExamination)) {
                    filteredUniExaminations.add(examination);
                }
            }
            trainingCourse.setTrainingCourseUniversityExaminations(filteredUniExaminations);
        }
    }

    public void commonAfterOverrideInverse(RudiApplicationDTO source, @MappingTarget RudiEduDataBaseDTO target) {
        target.setPrimaryUniversity(source.getTrainingCourse().getTrainingCourseUniversities().stream().filter(x -> x.getOrdNum().equals(1)).findFirst().orElse(null));
        List<TrainingCourseUniversityDTO> secondaryUniversities = source.getTrainingCourse().getTrainingCourseUniversities().stream().filter(x -> x.getOrdNum().equals(2)).toList();
        target.setSecondaryUniversities(secondaryUniversities);

        PersonDTO diplomaOwner = source.getTrainingCourse().getDiplomaOwner();
        if (Objects.nonNull(diplomaOwner)) {
            target.setDiplomaOwnerCivilId(diplomaOwner.getCivilId());
            target.setDiplomaOwnerFirstName(diplomaOwner.getFirstName());
            target.setDiplomaOwnerMiddleName(diplomaOwner.getMiddleName());
            target.setDiplomaOwnerLastName(diplomaOwner.getLastName());
            target.setDiplomaOwnerBirthDate(diplomaOwner.getBirthDate());
            target.setDiplomaOwnerBirthCountry(Objects.nonNull(diplomaOwner.getOriginCountry()) ? diplomaOwner.getOriginCountry().getName() : null);
        }

        if (!CollectionUtils.isEmpty(target.getSecondaryUniversities())) {
            target.setIsJointDegree(true);
        }
    }

    public void commonSarDiplomaRecAfterOverride(SarUdirecEduDataCommonDTO source, @MappingTarget RudiApplicationDTO target) {
        commonAfterOverride(source, target);

        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {


            if (Objects.isNull(source.getSchoolCountry()) || Objects.isNull(source.getSchoolCountry().getId())) {
                trainingCourse.setSchoolCountry(null);
            }

            List<TrainingCourseGraduationWayDTO> graduationWays = target.getTrainingCourse().getGraduationWays();
            if (Objects.isNull(graduationWays)) {
                graduationWays = new ArrayList<>();
            }
            List<TrainingCourseGraduationWayDTO> newGraduationWays = new ArrayList<>();
            graduationWaySectionToTrainingCourse(graduationWays, newGraduationWays, source.getTrainingCourseId(), source.getGraduationWayThesis(), GraduationWayType.THESIS.code(), source.getGraduationWayNotes());
            graduationWaySectionToTrainingCourse(graduationWays, newGraduationWays, source.getTrainingCourseId(), source.getGraduationWayExam(), GraduationWayType.EXAM.code(), source.getGraduationWayNotes());
            graduationWaySectionToTrainingCourse(graduationWays, newGraduationWays, source.getTrainingCourseId(), source.getGraduationWayThesisAndExam(), GraduationWayType.THESIS_AND_EXAM.code(), source.getGraduationWayNotes());
            graduationWaySectionToTrainingCourse(graduationWays, newGraduationWays, source.getTrainingCourseId(), source.getGraduationWayOther(), GraduationWayType.OTHER.code(), source.getGraduationWayNotes());
            target.getTrainingCourse().setGraduationWays(newGraduationWays);
        }

        List<ApplicationRecognitionPurposeDTO> arps = target.getApplicationRecognitionPurposes();
        List<ApplicationRecognitionPurposeDTO> newArps = new ArrayList<>();
        recognitionPurposeSectionToApplication(arps, newArps, source.getApplicationId(), source.getRecognitionPurposeContinueEducation(), RecognitionPurposeType.CONTINUE_EDUCATION.code(), source.getRecognitionPurposeNotes());
        recognitionPurposeSectionToApplication(arps, newArps, source.getApplicationId(), source.getRecognitionPurposeWork(), RecognitionPurposeType.WORK.code(), source.getRecognitionPurposeNotes());
        recognitionPurposeSectionToApplication(arps, newArps, source.getApplicationId(), source.getRecognitionPurposeProjectWork(), RecognitionPurposeType.PROJECT_WORK.code(), source.getRecognitionPurposeNotes());
        recognitionPurposeSectionToApplication(arps, newArps, source.getApplicationId(), source.getRecognitionPurposeOther(), RecognitionPurposeType.OTHER.code(), source.getRecognitionPurposeNotes());
        target.setApplicationRecognitionPurposes(newArps);
    }

    public void commonSarDiplomaRecAfterOverrideInverse(RudiApplicationDTO source, @MappingTarget SarUdirecEduDataCommonDTO target) {
        commonAfterOverrideInverse(source, target);
        target.setRecognitionPurposeContinueEducation(source.getApplicationRecognitionPurposes().stream().anyMatch(o -> o.getRecognitionPurpose().getId().equals(RecognitionPurposeType.CONTINUE_EDUCATION.code())));
        target.setRecognitionPurposeWork(source.getApplicationRecognitionPurposes().stream().anyMatch(o -> o.getRecognitionPurpose().getId().equals(RecognitionPurposeType.WORK.code())));
        target.setRecognitionPurposeProjectWork(source.getApplicationRecognitionPurposes().stream().anyMatch(o -> o.getRecognitionPurpose().getId().equals(RecognitionPurposeType.PROJECT_WORK.code())));
        Optional<ApplicationRecognitionPurposeDTO> oth = source.getApplicationRecognitionPurposes().stream().filter(o -> o.getRecognitionPurpose().getId().equals(RecognitionPurposeType.OTHER.code())).findFirst();
        target.setRecognitionPurposeOther(oth.isPresent());
        target.setRecognitionPurposeNotes(oth.isPresent() ? oth.get().getNotes() : "");

        if (Objects.nonNull(source.getTrainingCourse())) {
            fillPrevDiplomaUniversity(source, target);

            target.setGraduationWayThesis(source.getTrainingCourse().getGraduationWays().stream().anyMatch(o -> o.getGraduationWay().getId().equals(GraduationWayType.THESIS.code())));
            target.setGraduationWayThesisAndExam(source.getTrainingCourse().getGraduationWays().stream().anyMatch(o -> o.getGraduationWay().getId().equals(GraduationWayType.THESIS_AND_EXAM.code())));
            target.setGraduationWayExam(source.getTrainingCourse().getGraduationWays().stream().anyMatch(o -> o.getGraduationWay().getId().equals(GraduationWayType.EXAM.code())));
            Optional<TrainingCourseGraduationWayDTO> otherGraduationWay = source.getTrainingCourse().getGraduationWays().stream().filter(o -> o.getGraduationWay().getId().equals(GraduationWayType.OTHER.code())).findFirst();
            target.setGraduationWayOther(otherGraduationWay.isPresent());
            target.setGraduationWayNotes(otherGraduationWay.isPresent() ? otherGraduationWay.get().getNotes() : "");
        }
    }

}
