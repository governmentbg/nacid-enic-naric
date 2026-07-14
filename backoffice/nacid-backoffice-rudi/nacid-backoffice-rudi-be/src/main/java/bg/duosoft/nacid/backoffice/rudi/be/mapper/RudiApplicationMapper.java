package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.BgAddressOwner;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.*;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.LegalReasonMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.*;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.CommissionApplicationMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        PersonMapper.class,
        AddressMapper.class,
        ApplicantDiplomaNamesMapper.class,
        ApplicationAdditionalSubmissionMapper.class,
        ApplicationMapper.class,
        IntegerToBooleanMapper.class,
        ReferenceDataMapper.class,
        TrainingCourseMapper.class,
        ApplicationCommissionMemberMapper.class,
        ApplicationCommissionMemberStatementMapper.class,
        ApplicationRecognitionPurposeMapper.class,
        CommissionApplicationMapper.class,
        ApplicationRecognizedSpecialityMapper.class,
        ApplicationRecognizedDetailsMapper.class,
        SarApplicationMapper.class,
        ApplicationNotesMapper.class,
        ApplicationAttachedDocMapper.class,
        LegalReasonMapper.class
})
public abstract class RudiApplicationMapper extends BaseObjectMapper<RudiApplicationEntity, RudiApplicationDTO> {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Mapping(target = "application", source = ".")
    @Mapping(target = "bgAddressOwner", source = "bgAddressOwner")
    @Mapping(target = "representativeAuthorizedFlag", source = "representativeAuthorizedFlag")
    @Mapping(target = "submittedDocs", source = "submittedDocs")
    @Mapping(target = "trainingCourse", source = "trainingCourse")
    @Mapping(target = "applicationCommissionMembers", source = "applicationCommissionMembers")
    @Mapping(target = "applicationCommissionMemberStatements", source = "applicationCommissionMemberStatements")
    @Mapping(target = "applicationRecognitionPurposes", source = "applicationRecognitionPurposes")
    @Mapping(target = "commissionApplications", source = "commissionApplications")
    @Mapping(target = "recognizedSpecialities", source = "recognizedSpecialities")
    @Mapping(target = "applicationRecognizedDetails", source = "applicationRecognizedDetails")
    @Mapping(target = "sarApplication", source = "sarApplication")
    @Mapping(target = "legalReason", source = "legalReason")
    public abstract RudiApplicationDTO toDto(RudiApplicationEntity entity);

    @InheritInverseConfiguration
    public abstract RudiApplicationEntity toEntity(RudiApplicationDTO dto);

    @AfterMapping
    protected void afterToEntity(RudiApplicationDTO source, @MappingTarget RudiApplicationEntity target) {
        applicationMapper.afterToEntity(source.getApplication(), target);
        setTrainingCourseApplicationId(target);
        setApplicationCommissionMemberApplication(target);
        setApplicationCommissionMemberStatementApplication(target);
        setUniExaminationsApplication(target);
        setDiplomaExamAttachmentsApplication(target);
        setRecognizedDetailsApplication(target);
        setRecognizedSpecialityApplication(target);
        setRecognitionPurposesApplication(target);
        setSarApplicationApplication(target);
        setDefaultValues(target);
        setDefaultDiplomaOwnerForDocrecAndUdirecApps(target);
    }

    @AfterMapping
    protected void afterToDto(RudiApplicationEntity source, @MappingTarget RudiApplicationDTO target) {

    }

    private static void setTrainingCourseApplicationId(RudiApplicationEntity target) {
        TrainingCourseEntity trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            if (Objects.isNull(trainingCourse.getApplication())) {
                trainingCourse.setApplication(target);
            }
        }
    }

    private void setApplicationCommissionMemberStatementApplication(RudiApplicationEntity target) {
        if (!CollectionUtils.isEmpty(target.getApplicationCommissionMemberStatements())) {
            for (ApplicationCommissionMemberStatementEntity statement : target.getApplicationCommissionMemberStatements()) {
                statement.setApplication(target);
                if (Objects.nonNull(statement.getAttachedDoc())) {
                    statement.getAttachedDoc().setApplication(target);
                }
            }
        }
    }

    private void setUniExaminationsApplication(RudiApplicationEntity target) {
        TrainingCourseEntity trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            List<TrainingCourseUniversityExaminationEntity> trainingCourseUniversityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();
            if (!CollectionUtils.isEmpty(trainingCourseUniversityExaminations)) {
                for (TrainingCourseUniversityExaminationEntity uniExam : trainingCourseUniversityExaminations) {
                    List<ApplicationAttachedDocEntity> attachedDocs = uniExam.getAttachedDocs();
                    if (!CollectionUtils.isEmpty(attachedDocs)) {
                        for (ApplicationAttachedDocEntity attachedDoc : attachedDocs) {
                            attachedDoc.setApplication(target);
                        }
                    }
                }
            }
        }
    }

    private void setDiplomaExamAttachmentsApplication(RudiApplicationEntity target) {
        TrainingCourseEntity trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            List<ApplicationAttachedDocEntity> diplomaExaminationAttachedDocs = trainingCourse.getDiplomaExaminationAttachedDocs();

            if (!CollectionUtils.isEmpty(diplomaExaminationAttachedDocs)) {
                for (ApplicationAttachedDocEntity diplomaExaminationAttachedDoc : diplomaExaminationAttachedDocs) {
                    diplomaExaminationAttachedDoc.setApplication(target);
                }
            } else {
                trainingCourse.setDiplomaExaminationAttachedDocs(new ArrayList<>());
            }
        }
    }

    private void setApplicationCommissionMemberApplication(RudiApplicationEntity target) {
        if (target.getApplicationCommissionMembers() != null) {
            for (ApplicationCommissionMemberEntity applicationCommissionMemberEntity : target.getApplicationCommissionMembers()) {
                applicationCommissionMemberEntity.setApplication(target);

                ReferenceDataEntity eduLevel = applicationCommissionMemberEntity.getEduLevel();
                if (Objects.nonNull(eduLevel) && Objects.nonNull(eduLevel.getPk())) {
                    eduLevel.getPk().setDomain(ReferenceDataDomain.EDUCATION_LEVEL.domain());
                }
                if (Objects.nonNull(applicationCommissionMemberEntity.getLegalReason()) && Objects.isNull(applicationCommissionMemberEntity.getLegalReason().getId())) {
                    applicationCommissionMemberEntity.setLegalReason(null);
                }
            }

        }
    }

    private void setRecognizedDetailsApplication(RudiApplicationEntity target) {
        if (target.getApplicationRecognizedDetails() != null) {
            target.getApplicationRecognizedDetails().setApplication(target);
            target.getApplicationRecognizedDetails().setApplicationId(target.getId());
        }
    }

    private void setRecognizedSpecialityApplication(RudiApplicationEntity target) {
        if (!ObjectUtils.isEmpty(target.getRecognizedSpecialities())) {
            target.getRecognizedSpecialities().forEach(e -> e.setApplication(target));
        }
    }

    private void setRecognitionPurposesApplication(RudiApplicationEntity target) {
        if (!ObjectUtils.isEmpty(target.getApplicationRecognitionPurposes())) {
            target.getApplicationRecognitionPurposes().forEach(e -> e.setApplication(target));
        }
    }

    private void setSarApplicationApplication(RudiApplicationEntity target) {
        if (target.getSarApplication() != null) {
            target.getSarApplication().setApplication(target);
            target.getSarApplication().setApplicationId(target.getId());
        }
    }

    private void setDefaultValues(RudiApplicationEntity target) {
        String bgAddressOwner = target.getBgAddressOwner();
        if (!StringUtils.hasText(bgAddressOwner)) {
            target.setBgAddressOwner(BgAddressOwner.APPLICANT.code());
        }
    }

    private static void setDefaultDiplomaOwnerForDocrecAndUdirecApps(RudiApplicationEntity target) {
        TrainingCourseEntity trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(ApplicationType.RUDI.code(), target.getApplicationSubtype().getId());
            switch (type) {
                case RUDI_UNI_DIPLOMA_RECOGNITION, RUDI_DOC_DEGREE_RECOGNITION -> {
                    trainingCourse.setDiplomaOwner(target.getApplicant());
                }
            }
        }
    }

}
