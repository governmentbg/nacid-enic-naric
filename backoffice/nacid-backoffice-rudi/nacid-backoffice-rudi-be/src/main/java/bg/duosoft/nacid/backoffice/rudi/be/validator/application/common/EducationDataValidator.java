package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.GraduationWayType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.RecognitionPurposeType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.TrainingForm;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EducationDataValidator implements Validator<RudiApplicationDTO> {


    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        TrainingCourseDTO trainingCourse = obj.getTrainingCourse();
        rejectIfEmpty(errors, trainingCourse, "trainingCourse", "validation.field.required");

        if (Objects.nonNull(trainingCourse)) {
            if (Objects.nonNull(trainingCourse.getDiplomaDate())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getDiplomaDate(), LocalDate.now(), "diplomaDate", "m.validation.date.after.today");
            }
            if (StringUtils.hasText(trainingCourse.getDiplomaNumber())) {
                rejectIfTrue(errors, trainingCourse.getDiplomaNumber().length() > 50, "diplomaNumber", "m.validation.field.longer.than.50");
            }
            if (StringUtils.hasText(trainingCourse.getDiplomaSeries())) {
                rejectIfTrue(errors, trainingCourse.getDiplomaSeries().length() > 20, "diplomaSeries", "m.validation.field.longer.than.20");
            }
            if (StringUtils.hasText(trainingCourse.getDiplomaRegistrationNumber())) {
                rejectIfTrue(errors, trainingCourse.getDiplomaRegistrationNumber().length() > 20, "diplomaRegistrationNumber", "m.validation.field.longer.than.20");
            }
            if (Objects.nonNull(trainingCourse.getTrainingStart()) && Objects.nonNull(trainingCourse.getTrainingEnd())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getTrainingStart(), trainingCourse.getTrainingEnd(), "trainingStart", "m.validation.training.start.after.end");
            }
            if (Objects.nonNull(trainingCourse.getTrainingStart())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getTrainingStart(), LocalDate.now(), "trainingStart", "m.validation.date.after.today");
            }
            if (Objects.nonNull(trainingCourse.getTrainingEnd())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getTrainingEnd(), LocalDate.now(), "trainingEnd", "m.validation.date.after.today");
            }
            if (Objects.nonNull(trainingCourse.getThesisDefenceDate())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getThesisDefenceDate(), LocalDate.now(), "thesisDefenceDate", "m.validation.date.after.today");
            }
            if (Objects.nonNull(trainingCourse.getTrainingEnd()) && Objects.nonNull(trainingCourse.getDiplomaDate())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getTrainingEnd(), trainingCourse.getDiplomaDate(), "trainingEnd", "m.validation.year.after.diploma.date");
            }
            if (Objects.nonNull(trainingCourse.getTrainingStart()) && Objects.nonNull(trainingCourse.getDiplomaDate())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getTrainingStart(), trainingCourse.getDiplomaDate(), "trainingStart", "m.validation.year.after.diploma.date");
            }
            if (Objects.nonNull(trainingCourse.getPrevDiplomaGraduationDate()) && Objects.nonNull(trainingCourse.getDiplomaDate())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getPrevDiplomaGraduationDate(), trainingCourse.getDiplomaDate(), "prevDiplomaGraduationDate", "m.validation.year.after.diploma.date");
                rejectIfTrue(errors, trainingCourse.getPrevDiplomaGraduationDate().getYear() == trainingCourse.getDiplomaDate().getYear(), "prevDiplomaGraduationDate", "m.validation.year.equals.diploma.date");
            }
            if (Objects.nonNull(trainingCourse.getPrevDiplomaGraduationDate()) || Objects.nonNull(trainingCourse.getPrevDiplomaEduLevel()) || Objects.nonNull(trainingCourse.getPrevDiplomaSpeciality()) || Objects.nonNull(trainingCourse.getPrevDiplomaNotes())) {
                rejectIfTrue(errors, Objects.isNull(trainingCourse.getPrevDiplomaUniversity()) || Objects.isNull(trainingCourse.getPrevDiplomaUniversity().getId()), "prevDiplomaUniversity.id", "validation.field.required");
            }
            if (StringUtils.hasText(trainingCourse.getPrevDiplomaSpeciality())) {
                rejectIfTrue(errors, trainingCourse.getPrevDiplomaSpeciality().length() > 255, "prevDiplomaSpeciality", "m.validation.field.longer.than.255");
            }
            if (StringUtils.hasText(trainingCourse.getPrevDiplomaNotes())) {
                rejectIfTrue(errors, trainingCourse.getPrevDiplomaNotes().length() > 2000, "prevDiplomaNotes", "m.validation.field.longer.than.2000");
            }
            if (Objects.nonNull(trainingCourse.getSchoolGraduationDate()) && Objects.nonNull(trainingCourse.getDiplomaDate())) {
                rejectIfFirstDateIsAfterSecond(errors, trainingCourse.getSchoolGraduationDate(), trainingCourse.getDiplomaDate(), "schoolGraduationDate", "m.validation.year.after.diploma.date");
                rejectIfTrue(errors, trainingCourse.getSchoolGraduationDate().getYear() == trainingCourse.getDiplomaDate().getYear(), "schoolGraduationDate", "m.validation.year.equals.diploma.date");
            }
            if (!CollectionUtils.isEmpty(trainingCourse.getGraduationWays())) {
                Optional<TrainingCourseGraduationWayDTO> first = trainingCourse.getGraduationWays().stream().filter(e -> e.getGraduationWay().getId().equals(GraduationWayType.OTHER.code())).findFirst();
                if (first.isPresent()) {
                    rejectIfTrue(errors, !StringUtils.hasText(first.get().getNotes()) || first.get().getNotes().length() > 255, "graduationWayNotes", "m.validation.field.required.255");
                }
            }
            validateDissertationFields(trainingCourse, errors);
            TrainingCourseTrainingFormDTO trainingForm = trainingCourse.getTrainingForm();
            if (Objects.nonNull(trainingForm)) {
                String trainingFormId = CommonUtils.selectId(trainingForm.getTrainingForm());
                if (Objects.nonNull(trainingFormId) && trainingFormId.equals(TrainingForm.OTHER.code())) {
                    rejectIfTrue(errors, !StringUtils.hasText(trainingForm.getNotes()) || trainingForm.getNotes().length() > 255, "trainingFormNotes", "m.validation.field.required.255");
                }
            }

            if (StringUtils.hasText(trainingCourse.getSchoolName())) {
                rejectIfTrue(errors, trainingCourse.getSchoolName().length() > 255, "schoolName", "m.validation.field.longer.than.255");
            }

            if (StringUtils.hasText(trainingCourse.getSchoolCity())) {
                rejectIfTrue(errors, trainingCourse.getSchoolCity().length() > 100, "schoolCity", "m.validation.field.longer.than.100");
            }
            if (StringUtils.hasText(trainingCourse.getSchoolNotes())) {
                rejectIfTrue(errors, trainingCourse.getSchoolNotes().length() > 2000, "schoolNotes", "m.validation.field.longer.than.2000");
            }

            List<TrainingLocationDTO> trainingLocations = trainingCourse.getTrainingLocations();
            if (!CollectionUtils.isEmpty(trainingLocations)) {
                for (int i = 0; i < trainingLocations.size(); i++) {
                    if (!StringUtils.hasText(trainingLocations.get(i).getCity()) || trainingLocations.get(i).getCity().length() > 30) {
                        reject(errors, "trainingLocations[" + i + "].city", "m.validation.field.required.30");
                    }
                    if (Objects.isNull(trainingLocations.get(i).getCountry()) || !StringUtils.hasText(trainingLocations.get(i).getCountry().getId())) {
                        reject(errors, "trainingLocations[" + i + "].country.id", "validation.field.required");
                    }
                }
            }

            List<TrainingCourseSpecialityDTO> trainingCourseSpecialities = trainingCourse.getTrainingCourseSpecialities();
            if (!CollectionUtils.isEmpty(trainingCourseSpecialities)) {
                for (int i = 0; i < trainingCourseSpecialities.size(); i++) {
                    if (!StringUtils.hasText(trainingCourseSpecialities.get(i).getSpeciality()) || trainingCourseSpecialities.get(i).getSpeciality().length() > 255) {
                        reject(errors, "trainingCourseSpecialities[" + i + "].speciality", "m.validation.field.required.255");
                    }
                    if (StringUtils.hasText(trainingCourseSpecialities.get(i).getOriginalSpeciality()) && trainingCourseSpecialities.get(i).getOriginalSpeciality().length() > 255) {
                        reject(errors, "trainingCourseSpecialities[" + i + "].originalSpeciality", "m.validation.field.longer.than.255");
                    }
                }
            }

            String manualTempUniName = trainingCourse.getManualTempUniName();
            if (StringUtils.hasText(manualTempUniName)) {
                if (manualTempUniName.length() > 255) {
                    reject(errors, "manualTempUniName", "m.validation.field.longer.than.255");
                } else {
                    boolean ignoreCheck = false;
                    if (!CollectionUtils.isEmpty(trainingCourse.getTrainingCourseUniversities())) {
                        TrainingCourseUniversityDTO primaryUniversity = trainingCourse.getTrainingCourseUniversities().stream().filter(x -> x.getOrdNum().equals(1)).findFirst().orElse(null);
                        if (Objects.nonNull(primaryUniversity) && StringUtils.hasText(primaryUniversity.getUniversityNameTranslated())) {
                            ignoreCheck = true;
                        }
                    }
                    if (!ignoreCheck && manualTempUniName.split(",").length < 3) {
                        reject(errors, "manualTempUniName", "m.validation.field.manualTempUniName.format");
                    }
                }
            }

            if (!CollectionUtils.isEmpty(trainingCourse.getTrainingCourseUniversities())) {
                TrainingCourseUniversityDTO primaryUniversity = trainingCourse.getTrainingCourseUniversities().stream().filter(x -> x.getOrdNum().equals(1)).findFirst().orElse(null);
                if (Objects.nonNull(primaryUniversity)) {
                    if (!StringUtils.hasText(primaryUniversity.getUniversityNameTranslated()) || primaryUniversity.getUniversityNameTranslated().length() > 255) {
                        reject(errors, "primaryUniversity.universityNameTranslated", "m.validation.field.required.255");
                    }
                    if (StringUtils.hasText(primaryUniversity.getUniversityContact()) && primaryUniversity.getUniversityContact().length() > 300) {
                        reject(errors, "primaryUniversity.universityContact", "m.validation.field.longer.than.300");
                    }
                }
                if (trainingCourse.getTrainingCourseUniversities().size() > 1) {
                    List<TrainingCourseUniversityDTO> trainingCourseUniversities = trainingCourse.getTrainingCourseUniversities().stream().filter(x -> x.getOrdNum().equals(2)).toList();
                    if (!CollectionUtils.isEmpty(trainingCourseUniversities)) {
                        for (int i = 0; i < trainingCourseUniversities.size(); i++) {
                            if (!StringUtils.hasText(trainingCourseUniversities.get(i).getUniversityNameTranslated()) || trainingCourseUniversities.get(i).getUniversityNameTranslated().length() > 255) {
                                reject(errors, "secondaryUniversities[" + i + "].universityNameTranslated", "m.validation.field.required.255");
                            }
                            if (StringUtils.hasText(trainingCourseUniversities.get(i).getUniversityContact()) && trainingCourseUniversities.get(i).getUniversityContact().length() > 300) {
                                reject(errors, "secondaryUniversities[" + i + "].universityContact", "m.validation.field.longer.than.300");
                            }
                        }
                    }
                }
            }
        }

        if (!CollectionUtils.isEmpty(obj.getApplicationRecognitionPurposes())) {
            Optional<ApplicationRecognitionPurposeDTO> first = obj.getApplicationRecognitionPurposes().stream().filter(e -> e.getRecognitionPurpose().getId().equals(RecognitionPurposeType.OTHER.code())).findFirst();
            if (first.isPresent()) {
                rejectIfTrue(errors, !StringUtils.hasText(first.get().getNotes()) || first.get().getNotes().length() > 255, "recognitionPurposeNotes", "m.validation.field.required.255");
            }
        }

        return errors;
    }


    private void validateDissertationFields(TrainingCourseDTO trainingCourse, List<ValidationError> errors) {
        validateRequiredAndMax1500(errors,trainingCourse.getThesisTopic(), "thesisTopic");
        validateRequiredAndMax1500(errors,trainingCourse.getThesisTopicEn(), "thesisTopicEn");
        validateRequiredAndMax1500(errors, trainingCourse.getScientificSupervisor(), "scientificSupervisor");
        validateRequiredAndMax1500(errors, trainingCourse.getScientificSupervisorEn(), "scientificSupervisorEn");
        validateRequiredAndMax1500(errors, trainingCourse.getReviewers(), "reviewers");
        validateRequiredAndMax1500(errors, trainingCourse.getReviewersEn(), "reviewersEn");
        validateRequiredAndMax1500(errors, trainingCourse.getJuryChair(), "juryChair");
        validateRequiredAndMax1500(errors, trainingCourse.getJuryChairEn(), "juryChairEn");
        validateRequiredAndMax1500(errors, trainingCourse.getJuryMembers(), "juryMembers");
        validateRequiredAndMax1500(errors, trainingCourse.getJuryMembersEn(), "juryMembersEn");
        validateRequiredAndMax1500(errors, trainingCourse.getThesisAnnotation(), "thesisAnnotation");
        validateRequiredAndMax1500(errors, trainingCourse.getThesisAnnotationEn(), "thesisAnnotationEn");
    }

    private void validateRequiredAndMax1500(List<ValidationError> errors, String value, String fieldName) {
        rejectIfTrue(errors, StringUtils.hasText(value) && value.length() > 1500, fieldName, "m.validation.field.required.1500");
    }
}
