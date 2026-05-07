package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.GraduationWayType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.FoApplicationDataConverter;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.FoNaturalPersonMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.diploma.FoEducationDiplomaMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.location.FoTrainingLocationMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.prev_university.FoEducationPreviousUniversityMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.school.FoEducationSchoolMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.speciality.FoSpecialityMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.thesis.FoEducationThesisMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.training_form.FoTrainingFormMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.nomenclature.FoProfGroupMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.sar.FoSarDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.RudiEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.UniversityDataDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FoConverter {

    private final FoNaturalPersonMapper naturalPersonMapper;
    private final FoReferenceDataMapper referenceDataMapper;
    private final FoEducationDiplomaMapper educationDiplomaMapper;
    private final FoTrainingLocationMapper trainingLocationMapper;
    private final FoTrainingFormMapper trainingFormMapper;
    private final FoEducationSchoolMapper educationSchoolMapper;
    private final FoEducationPreviousUniversityMapper educationPreviousUniversityMapper;
    private final FoSpecialityMapper specialityMapper;
    private final FoProfGroupMapper profGroupMapper;
    private final FoEducationThesisMapper educationThesisMapper;
    private final FoSarDataMapper sarDataMapper;
    private final FoApplicationDataConverter applicationDataConverter;

    private RudiApplicationDTO init() {
        RudiApplicationDTO boApp = new RudiApplicationDTO();
        boApp.setApplication(new ApplicationDTO());
        boApp.setTrainingCourse(new TrainingCourseDTO());
        return boApp;
    }

    public RudiApplicationDTO convertApplication(CommonApplicationDTO foApp) {
        RudiApplicationDTO boApp = init();
        ApplicationDTO application = boApp.getApplication();
        setApplicationType(application, foApp);
        applicationDataConverter.setCommonApplicationData(application, foApp);

        if (foApp instanceof UniChecksApplicationDTO sarApp) {
            convertSarAppDetails(boApp, sarApp);
        } else if (foApp instanceof HeRecognitionApplicationDTO udirecApp) {
            convertUdirecAppDetails(boApp, udirecApp);
        } else if (foApp instanceof DocDegreesApplicationDTO docrecApp) {
            convertDocrecAppDetails(boApp, docrecApp);
        }

        return boApp;
    }

    private void convertSarAppDetails(RudiApplicationDTO boApp, UniChecksApplicationDTO foApp) {
        UniChecksEducationDetailsDTO educationDetails = foApp.getEducationDetails();
        if (Objects.nonNull(educationDetails)) {
            setBaseTrainingCourseData(boApp, educationDetails);
            setSarTrainingCourseData(boApp, educationDetails);
            boApp.setSarApplication(sarDataMapper.toSarApplicationData(educationDetails));
        }
    }

    private void setRecognitionCategoday(RudiApplicationDTO boApp, bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO foRecognitionCategory) {
        TrainingCourseDTO trainingCourse = boApp.getTrainingCourse();
        if (Objects.nonNull(trainingCourse) && Objects.nonNull(foRecognitionCategory)) {
            trainingCourse.setRecognitionCategory(referenceDataMapper.toReferenceData(foRecognitionCategory));
        }
    }

    private void setSarTrainingCourseData(RudiApplicationDTO boApp, UniChecksEducationDetailsDTO educationDetails) {
        TrainingCourseDTO trainingCourse = boApp.getTrainingCourse();
        trainingCourse.setQualification(StringUtils.hasText(educationDetails.getGainedQualification()) ? educationDetails.getGainedQualification() : null);
        trainingCourse.setOriginalQualification(StringUtils.hasText(educationDetails.getOriginalGainedQualification()) ? educationDetails.getOriginalGainedQualification() : null);

        trainingCourse.setTrainingCourseSpecialities(specialityMapper.toTrainingCourseSpecialityList(educationDetails.getSpecialities()));
        trainingCourse.setDiplomaOwner(naturalPersonMapper.toNaturalPersonDto(educationDetails.getDiplomaHolder()));
        trainingCourse.setDiplomaOwnerEan(educationDetails.getDiplomaHolderEan());
        setRecognitionCategoday(boApp, educationDetails.getRecognitionCategory());
    }

    private void convertUdirecAppDetails(RudiApplicationDTO boApp, HeRecognitionApplicationDTO foApp) {
        HeEducationDetailsDTO educationDetails = foApp.getEducationDetails();
        if (Objects.nonNull(educationDetails)) {
            setBaseTrainingCourseData(boApp, educationDetails);
            setUdirecTrainingCourseData(boApp, educationDetails);
            setUdirecRudiAppData(boApp, educationDetails);
        }
    }

    private void convertDocrecAppDetails(RudiApplicationDTO boApp, DocDegreesApplicationDTO foApp) {
        DocEducationDetailsDTO educationDetails = foApp.getEducationDetails();
        if (Objects.nonNull(educationDetails)) {
            setBaseTrainingCourseData(boApp, educationDetails);
            setDocrecTrainingCourseData(boApp, educationDetails);
        }
    }

    private void setDocrecTrainingCourseData(RudiApplicationDTO boApp, DocEducationDetailsDTO educationDetails) {
        TrainingCourseDTO trainingCourse = boApp.getTrainingCourse();
        trainingCourse.setProfGroup(profGroupMapper.toBoProfGroup(educationDetails.getGainedLevelProfGroup()));

        educationPreviousUniversityMapper.overridePreviousUniversityData(educationDetails.getPreviousUniversityDiploma(), trainingCourse);
        educationThesisMapper.overrideThesisData(educationDetails, trainingCourse);
        setRecognitionCategoday(boApp, educationDetails.getRecognitionCategory());
    }

    private void setUdirecRudiAppData(RudiApplicationDTO boApp, HeEducationDetailsDTO educationDetails) {
        setRecognitionPurposeData(boApp, educationDetails);
    }

    private void setRecognitionPurposeData(RudiApplicationDTO boApp, HeEducationDetailsDTO educationDetails) {
        List<bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO> foRecognitionPurposes = educationDetails.getRecognitionAim();
        if (!CollectionUtils.isEmpty(foRecognitionPurposes)) {
            List<ReferenceDataDTO> boRecognitionPurposes = foRecognitionPurposes.stream().map(referenceDataMapper::toReferenceData).toList();
            List<ApplicationRecognitionPurposeDTO> applicationRecognitionPurposeList = new ArrayList<>();

            for (ReferenceDataDTO boRecognitionPurpose : boRecognitionPurposes) {
                ApplicationRecognitionPurposeDTO appRecPurpose = new ApplicationRecognitionPurposeDTO();
                appRecPurpose.setRecognitionPurpose(boRecognitionPurpose);

                if (GraduationWayType.OTHER.code().equals(boRecognitionPurpose.getId())) {
                    appRecPurpose.setNotes(educationDetails.getRecognitionAimOtherDetails());
                }
                applicationRecognitionPurposeList.add(appRecPurpose);
            }
            boApp.setApplicationRecognitionPurposes(applicationRecognitionPurposeList);
        }
    }

    private void setUdirecTrainingCourseData(RudiApplicationDTO boApp, HeEducationDetailsDTO educationDetails) {
        TrainingCourseDTO trainingCourse = boApp.getTrainingCourse();
        if (Objects.nonNull(educationDetails)) {
            trainingCourse.setQualification(StringUtils.hasText(educationDetails.getGainedQualification()) ? educationDetails.getGainedQualification() : null);
            trainingCourse.setTrainingCourseSpecialities(specialityMapper.toTrainingCourseSpecialityList(educationDetails.getSpecialities()));
            educationSchoolMapper.overrideSchoolData(educationDetails.getHighSchoolDiploma(), trainingCourse);
            educationPreviousUniversityMapper.overridePreviousUniversityData(educationDetails.getPreviousUniversityDiploma(), trainingCourse);
        }
    }

    private void setBaseTrainingCourseData(RudiApplicationDTO boApp, RudiEducationDetailsDTO foEducation) {
        TrainingCourseDTO trainingCourse = boApp.getTrainingCourse();
        if (Objects.nonNull(foEducation)) {
            educationDiplomaMapper.overrideDiplomaData(foEducation.getDiploma(), trainingCourse);
            trainingCourse.setTrainingLocations(trainingLocationMapper.toTrainingLocationList(foEducation.getEducationPlaces()));
            trainingCourse.setOriginalEduLevelName(foEducation.getOriginalGainedLevel());
            trainingCourse.setOriginalEduLevelTranslated(foEducation.getOriginalGainedLevelTranslated());
            setTrainingData(trainingCourse, foEducation);
            setUniversityData(trainingCourse, foEducation);
        }
    }

    private void setUniversityData(TrainingCourseDTO trainingCourse, RudiEducationDetailsDTO educationDetails) {
        List<UniversityDataDTO> foUniversityData = educationDetails.getUniversitiesData();

        if (!CollectionUtils.isEmpty(foUniversityData)) {
            UniversityDataDTO foBaseUniversity = foUniversityData.get(0);
            trainingCourse.setBaseUniversity(createUniversity(foBaseUniversity));
        }
    }

    private UniversityDTO createUniversity(UniversityDataDTO foBaseUniversity) {
        UniversityDTO boBaseUniversity = new UniversityDTO();
        boBaseUniversity.setId(foBaseUniversity.getNameId());
        boBaseUniversity.setBgName(foBaseUniversity.getName());
        return boBaseUniversity;
    }

    private void setTrainingData(TrainingCourseDTO trainingCourse, RudiEducationDetailsDTO foEducation) {
        trainingCourse.setTrainingStart(DateUtils.convertYearToLocalDate(foEducation.getStartOfEducation()));
        trainingCourse.setTrainingEnd(DateUtils.convertYearToLocalDate(foEducation.getEndOfEducation()));

        String educationDuration = foEducation.getEducationDuration();
        if (StringUtils.hasText(educationDuration)) {
            trainingCourse.setTrainingDuration(Double.parseDouble(educationDuration));
        }

        trainingCourse.setDurationUnit(referenceDataMapper.toReferenceData(foEducation.getEducationDurationType()));
        trainingCourse.setTrainingForm(trainingFormMapper.toTrainingForm(foEducation.getEducationFormWrapper()));

        String credits = foEducation.getCredits();
        if (StringUtils.hasText(credits)) {
            trainingCourse.setCredits(Double.parseDouble(credits));
        }

        setGraduationWays(trainingCourse, foEducation);
    }

    private void setGraduationWays(TrainingCourseDTO trainingCourse, RudiEducationDetailsDTO foEducation) {
        List<bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO> foGraduationWays = foEducation.getGraduationWay();
        if (!CollectionUtils.isEmpty(foGraduationWays)) {
            List<ReferenceDataDTO> boGraduationWays = foGraduationWays.stream().map(referenceDataMapper::toReferenceData).toList();
            List<TrainingCourseGraduationWayDTO> trainingCourseGraduationWayList = new ArrayList<>();

            for (ReferenceDataDTO boGraduationWay : boGraduationWays) {
                TrainingCourseGraduationWayDTO trainingCourseGraduationWay = new TrainingCourseGraduationWayDTO();
                trainingCourseGraduationWay.setGraduationWay(boGraduationWay);

                if (GraduationWayType.OTHER.code().equals(boGraduationWay.getId())) {
                    trainingCourseGraduationWay.setNotes(foEducation.getGraduationWayOtherDetails());
                }
                trainingCourseGraduationWayList.add(trainingCourseGraduationWay);
            }
            trainingCourse.setGraduationWays(trainingCourseGraduationWayList);
        }
    }

    private void setApplicationType(ApplicationDTO application, CommonApplicationDTO foApp) {
        ApplicationTypeDTO rudiApplicationType = new ApplicationTypeDTO(ApplicationType.RUDI.code());
        application.setApplicationType(rudiApplicationType);
        ApplicationSubtypeDTO applicationSubtype = new ApplicationSubtypeDTO();
        applicationSubtype.setApplicationType(rudiApplicationType);
        application.setApplicationSubtype(applicationSubtype);

        if (foApp instanceof UniChecksApplicationDTO) {
            applicationSubtype.setId(ApplicationSubType.RUDI_SAR.appSubType());
        } else if (foApp instanceof HeRecognitionApplicationDTO) {
            applicationSubtype.setId(ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION.appSubType());
        } else if (foApp instanceof DocDegreesApplicationDTO) {
            applicationSubtype.setId(ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION.appSubType());
        }
    }

}
