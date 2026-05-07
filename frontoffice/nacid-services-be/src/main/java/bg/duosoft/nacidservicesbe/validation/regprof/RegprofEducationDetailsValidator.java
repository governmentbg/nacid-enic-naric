package bg.duosoft.nacidservicesbe.validation.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.services.regprof.*;
import bg.duosoft.nacidservicesbe.validation.common.education.EducationsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.12.2022
 * Time: 16:12
 */
@Component
public class RegprofEducationDetailsValidator implements EducationsValidator<RegprofEducationDetailsDTO> {

    @Override
    public List<ValidationError> validate(RegprofEducationDetailsDTO regprofEducationDetailsDTO, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();

        rejectIfEmptyString(errors, regprofEducationDetailsDTO.getCountry() != null ? regprofEducationDetailsDTO.getCountry().getId(): null, "country.id", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfFalse(errors, regprofEducationDetailsDTO.isNonRevokedRightToPractice(), "nonRevokedRightToPractice", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, regprofEducationDetailsDTO.getProfessionalQualificationRequested(), "professionalQualificationRequested", ValidationMessageCodes.REQUIRED_CODE);

        if(regprofEducationDetailsDTO.isEducationSelected()){
            validateEducation(errors, regprofEducationDetailsDTO.getEducation());
        }
        if(regprofEducationDetailsDTO.isExperienceSelected()){
            validateExperience(errors, regprofEducationDetailsDTO.getExperience());
        }
        rejectIfEmptyString(errors, regprofEducationDetailsDTO.getServiceType() != null ? regprofEducationDetailsDTO.getServiceType().getId(): null, "serviceType.id", ValidationMessageCodes.REQUIRED_CODE);
        return errors;
    }

    private void validateEducation(List<ValidationError> errors, RegprofEducationDTO education){
        rejectIfEmpty(errors, education, "education", ValidationMessageCodes.REQUIRED_CODE);
        if(education != null){
            rejectIfEmpty(errors, education.getKind(), "education.kind", ValidationMessageCodes.REQUIRED_CODE);
            if(education.getKind() != null){
                if(education.getKind().equals(EducationType.AFTER_DIPLOMA_QUALIFICATION)){
                    validateEducationEntry(errors, education.getEducationEntryADQ(), "education.educationEntryADQ");
                    validateEducationEntry(errors, education.getEducationEntryHigher(), "education.educationEntryHigher");
                } else if(education.getKind().equals(EducationType.HIGHER_EDUCATION)){
                    validateEducationEntry(errors, education.getEducationEntryHigher(), "education.educationEntryHigher");
                } else {
                    validateEducationEntry(errors, education.getEducationEntrySecondary(), "education.educationEntrySecondary");
                }
            }
        }
    }

    private void validateEducationEntry(List<ValidationError> errors, RegprofEducationEntryDTO entry, String basePointer) {
        rejectIfEmpty(errors, entry, basePointer, ValidationMessageCodes.REQUIRED_CODE);
        if (entry != null) {
            rejectIfEmpty(errors, entry.getDocumentKind() != null ? entry.getDocumentKind().getId() : null, basePointer + ".documentKind.id", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfEmptyString(errors, entry.getProfessionalQualification(), basePointer + ".professionalQualification", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfStringLengthBigger(errors, entry.getProfessionalQualification(), 255, basePointer + ".professionalQualification");
            validateWithSpecialities(errors, entry, basePointer);
            if (!StringUtils.hasText(entry.getNewEducationInstitutionName())) {
                rejectIfEmptyString(errors, entry.getOldEducationInstitutionName(), basePointer + ".oldEducationInstitutionName", ValidationMessageCodes.REQUIRED_CODE);
            }
            if (!StringUtils.hasText(entry.getOldEducationInstitutionName())) {
                rejectIfEmptyString(errors, entry.getNewEducationInstitutionName(), basePointer + ".newEducationInstitutionName", ValidationMessageCodes.REQUIRED_CODE);
            }
            rejectIfStringLengthBigger(errors, entry.getOldEducationInstitutionName(), 255, basePointer + ".oldEducationInstitutionName");
            rejectIfStringLengthBigger(errors, entry.getNewEducationInstitutionName(), 255, basePointer + ".newEducationInstitutionName");
            rejectIfStringLengthBigger(errors, entry.getDocumentNumber(), 50, basePointer + ".documentNumber");
            rejectIfStringLengthBigger(errors, entry.getDocumentSeries(), 30, basePointer + ".documentSeries");
            rejectIfStringLengthBigger(errors, entry.getDocumentRegistrationNumber(), 30, basePointer + ".documentRegistrationNumber");
        }
    }

    private void validateExperience(List<ValidationError> errors, RegprofExperienceDTO experience){
        rejectIfEmpty(errors, experience, "experience", ValidationMessageCodes.REQUIRED_CODE);
        if(experience != null){
            rejectIfEmptyString(errors, experience.getProfession(), "experience.profession", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfStringLengthBigger(errors, experience.getProfession(), 255, "experience.profession");
            rejectIfEmpty(errors, experience.getExperienceDocuments() == null || experience.getExperienceDocuments().size() == 0 ? null: experience.getExperienceDocuments(), "experience.experienceDocuments", ValidationMessageCodes.REQUIRED_CODE);
            if(experience.getExperienceDocuments() != null){
                int i = 0;
                for(ExperienceDocumentDTO doc: experience.getExperienceDocuments()){
                    rejectIfEmptyString(errors, doc.getType() != null ? doc.getType().getId() : null, String.format("experience.experienceDocuments.%s.type.id", i), ValidationMessageCodes.REQUIRED_CODE);
                    rejectIfEmptyString(errors, doc.getInstitutionName(), String.format("experience.experienceDocuments.%s.institutionName", i), ValidationMessageCodes.REQUIRED_CODE);
                    rejectIfStringLengthBigger(errors, doc.getInstitutionName(), 100, String.format("experience.experienceDocuments.%s.institutionName", i));
                    rejectIfStringLengthBigger(errors, doc.getDocumentNumber(), 100, String.format("experience.experienceDocuments.%s.documentNumber", i));
                    rejectIfEmpty(errors, doc.getWorkPeriods() == null || doc.getWorkPeriods().size() == 0 ? null: doc.getWorkPeriods() , String.format("experience.experienceDocuments.%s.workPeriods", i), ValidationMessageCodes.REQUIRED_CODE);
                    if(doc.getWorkPeriods() != null){
                        int y = 0;
                        for(WorkPeriodDTO workPeriod: doc.getWorkPeriods()){
                            rejectIfEmpty(errors, workPeriod.getFromDate(), String.format("experience.experienceDocuments.%s.workPeriods.%s.fromDate", i, y), ValidationMessageCodes.REQUIRED_CODE);
                            rejectIfEmpty(errors, workPeriod.getToDate(), String.format("experience.experienceDocuments.%s.workPeriods.%s.toDate", i, y), ValidationMessageCodes.REQUIRED_CODE);
                            rejectIfEmptyString(errors, workPeriod.getWorkDayHours() != null? workPeriod.getWorkDayHours().getId() : null, String.format("experience.experienceDocuments.%s.workPeriods.%s.workDayHours.id", i, y), ValidationMessageCodes.REQUIRED_CODE);
                            y++;
                        }
                    }
                    i++;
                }
            }
        }
    }
}
