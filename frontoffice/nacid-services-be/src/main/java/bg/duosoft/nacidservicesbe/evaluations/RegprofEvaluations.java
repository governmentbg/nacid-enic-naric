package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.*;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.12.2022
 * Time: 14:53
 */
@Component
@RequiredArgsConstructor
public class RegprofEvaluations implements BaseApplicationEvaluations<RegprofApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(RegprofApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();
        CommonApplicationEvaluationsUtils.evaluateDeclarations(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateCertificateReceiveForm(application, evaluations);
        evaluatePerson(application.getApplicantDetails() != null && application.getApplicantDetails().getApplicant() != null ?
                        application.getApplicantDetails().getApplicant().getNaturalPerson(): null,
                evaluations);

        evaluateEducationDetails(application.getEducationDetails(), evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluatePerson(NaturalPersonDTO person, List<EvaluationDTO> evaluations){
        boolean dateOfBirth = false;
        boolean birthCountry = false;
        boolean birthPlace = false;
        if(person != null){
            dateOfBirth = person.getDateOfBirth() != null;
            birthCountry = person.getBirthCountry() != null && StringUtils.hasText(person.getBirthCountry().getId());
            birthPlace = person.getBirthPlace() != null || (person.getBirthSettlement() != null && StringUtils.hasText(person.getBirthSettlement().getId()));
        }
        evaluations.add(new EvaluationDTO("rule.applicantDetails.dateOfBirth", dateOfBirth));
        evaluations.add(new EvaluationDTO("rule.applicantDetails.birthCountry", birthCountry));
        evaluations.add(new EvaluationDTO("rule.applicantDetails.city", birthPlace));
    }

    public void evaluateEducationDetails(RegprofEducationDetailsDTO educationDetails, List<EvaluationDTO> evaluations){
        boolean country = false;
        boolean educationOrExperience = false;
        boolean professionalQualificationRequested = false;
        boolean serviceType = false;
        if(educationDetails != null){
            country = educationDetails.getCountry() != null && StringUtils.hasText(educationDetails.getCountry().getId());
            educationOrExperience = educationDetails.isEducationSelected() || educationDetails.isExperienceSelected();
            professionalQualificationRequested = StringUtils.hasText(educationDetails.getProfessionalQualificationRequested());

            if(educationDetails.isEducationSelected()){
                evaluateEducation(educationDetails.getEducation(), evaluations);
            }
            if(educationDetails.isExperienceSelected()){
                evaluateExperience(educationDetails.getExperience(), evaluations);
            }
            serviceType = educationDetails.getServiceType() != null && StringUtils.hasText(educationDetails.getServiceType().getId());
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.serviceType", serviceType));
        evaluations.add(new EvaluationDTO("rule.educationDetails.country", country));
        evaluations.add(new EvaluationDTO("rule.educationDetails.educationOrExperience", educationOrExperience));
        evaluations.add(new EvaluationDTO("rule.educationDetails.professionalQualificationRequested", professionalQualificationRequested));
    }

    public void evaluateEducation(RegprofEducationDTO education, List<EvaluationDTO> evaluations){
        boolean kind = false;
        boolean educationInstitutionName = false;
        boolean professionalQualification = false;
        boolean specialities = false;
        boolean documentKind = false;

        if(education != null){
            kind = education.getKind() != null;
            if(education.getKind() != null) {
                switch (education.getKind()){
                    case AFTER_DIPLOMA_QUALIFICATION: case HIGHER_EDUCATION:
                        educationInstitutionName = hasProfessionalInstitution(education.getEducationEntryHigher());
                        professionalQualification = hasQualification(education.getEducationEntryHigher());
                        specialities = hasSpecialities(education.getEducationEntryHigher());
                        documentKind = hasDocumentKind(education.getEducationEntryHigher());
                        break;
                    case PROFESSIONAL_EDUCATION: case SECONDARY_PROFESSIONAL_EDUCATION:
                        educationInstitutionName = hasProfessionalInstitution(education.getEducationEntrySecondary());
                        professionalQualification = hasQualification(education.getEducationEntrySecondary());
                        specialities = hasSpecialities(education.getEducationEntrySecondary());
                        documentKind = hasDocumentKind(education.getEducationEntrySecondary());
                        break;
                }
            }
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.education.kind", kind));
        evaluations.add(new EvaluationDTO("rule.educationDetails.education.educationInstitutionName", educationInstitutionName));
        evaluations.add(new EvaluationDTO("rule.educationDetails.education.professionalQualification", professionalQualification));
        evaluations.add(new EvaluationDTO("rule.educationDetails.education.specialities", specialities));
        evaluations.add(new EvaluationDTO("rule.educationDetails.education.documentKind", documentKind));
    }

    public void evaluateExperience(RegprofExperienceDTO experience, List<EvaluationDTO> evaluations){
        boolean profession = false;
        boolean experienceDocumentsType = false;
        boolean experienceDocumentsInstitution = false;
        boolean experienceDocumentsWorkPeriod = false;

        if(experience != null){
            profession = StringUtils.hasText(experience.getProfession());
            boolean hasDocs = experience.getExperienceDocuments() != null && experience.getExperienceDocuments().size()>0;
            experienceDocumentsType = hasDocs && experience.getExperienceDocuments().get(0).getType() != null
                    && StringUtils.hasText(experience.getExperienceDocuments().get(0).getType().getId());
            experienceDocumentsInstitution = hasDocs && StringUtils.hasText(experience.getExperienceDocuments().get(0).getInstitutionName());
            experienceDocumentsWorkPeriod = hasDocs && experience.getExperienceDocuments().get(0).getWorkPeriods() != null &&  experience.getExperienceDocuments().get(0).getWorkPeriods().size() > 0;
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.experience.profession", profession));
        evaluations.add(new EvaluationDTO("rule.educationDetails.experience.experienceDocuments.type", experienceDocumentsType));
        evaluations.add(new EvaluationDTO("rule.educationDetails.experience.experienceDocuments.institutionName", experienceDocumentsInstitution));
        evaluations.add(new EvaluationDTO("rule.educationDetails.experience.experienceDocuments.workPeriod", experienceDocumentsWorkPeriod));
    }

    public boolean hasProfessionalInstitution(RegprofEducationEntryDTO entry){
        if(entry != null){
            return StringUtils.hasText(entry.getNewEducationInstitutionName()) || StringUtils.hasText(entry.getOldEducationInstitutionName());
        }
        return false;
    }

    public boolean hasQualification(RegprofEducationEntryDTO entry){
        if(entry != null){
            return StringUtils.hasText(entry.getProfessionalQualification());
        }
        return false;
    }

    public boolean hasSpecialities(RegprofEducationEntryDTO entry){
        if(entry != null && entry.getSpecialities() != null && entry.getSpecialities().size() > 0 ){
            return entry.getSpecialities().stream().filter(spec -> spec == null || !StringUtils.hasText(spec.getName())).count() == 0;
        }
        return false;
    }

    public boolean hasDocumentKind(RegprofEducationEntryDTO entry){
        if(entry != null && entry.getDocumentKind() != null){
            return entry.getDocumentKind().getId() != null;
        }
        return false;
    }
}
