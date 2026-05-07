package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgappstatus.CfgAppStatusClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InitialConstraintDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.SarApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.status.StatusInsertValidator;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.AppStatusDataInsertStatusMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.provider.RudiStatusDataMapperProvider;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final RudiApplicationService rudiApplicationService;
    private final CfgAppStatusClient cfgAppStatusClient;
    private final RudiStatusDataMapperProvider mapperProvider;
    private final AppStatusDataInsertStatusMapper insertStatusMapper;
    private final StatusInsertValidator statusInsertValidator;
    private final RudiStatusService rudiStatusService;

    public RudiApplicationService getRudiApplicationService() {
        return this.rudiApplicationService;
    }

    public RudiStatusDataBaseDTO getStatusSectionData(Integer applicationId) {
        RudiApplicationDTO app = rudiApplicationService.selectById(applicationId);
        return mapperProvider.getMapper(app).toStatusDataSection(app);
    }

    @Override
    public InitialConstraintDTO examineStatusInitialData(RudiApplicationDTO rudiApplication) {
        InitialConstraintDTO initialConstraint = InitialConstraintDTO.newInstance();
        List<InitialConstraintDTO.Tab> tabs = initialConstraint.getTabs();

        ApplicationDTO application = rudiApplication.getApplication();
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(application.getApplicationType().getId(), application.getApplicationSubtype().getId());
        TrainingCourseDTO trainingCourse = rudiApplication.getTrainingCourse();

        tabs.add(createEducationConstraintsTab(trainingCourse, type));

        initialConstraint.fillIsAllAccomplished();
        return initialConstraint;
    }

    @Override
    @LogObjectChange(service = "RudiApplicationStatusChange", id = "#statusDataSection.applicationId", before = "#root.target.getStatusSectionData(#statusDataSection.applicationId)", after = "#result", operation = "'update'")
    public RudiStatusDataBaseDTO insertStatus(RudiStatusDataBaseDTO statusDataSection) {
        BadRequestValidator.validateRequest(statusInsertValidator, statusDataSection);
        Integer applicationId = statusDataSection.getApplicationId();
        rudiStatusService.insertRudiStatus(applicationId, insertStatusMapper.toInsertActionDTO(statusDataSection));

        RudiApplicationDTO application = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException("Application not found ! ID: " + applicationId);
        }

        mapperProvider.getMapper(application).overrideApplicationData(statusDataSection, application);
        RudiApplicationDTO result = rudiApplicationService.save(application, ValidationScope.STATUS_DATA,false);
        return mapperProvider.getMapper(result).toStatusDataSection(result);
    }
    
    private InitialConstraintDTO.Tab createEducationConstraintsTab(TrainingCourseDTO trainingCourse, ApplicationSubType type) {
        InitialConstraintDTO.Tab education = InitialConstraintDTO.Tab.newInstance("t.educationData");
        String appSuffix = "";

        switch (type) {
            case RUDI_SAR -> appSuffix = "sarApp";
            case RUDI_UNI_DIPLOMA_RECOGNITION -> appSuffix = "uniDiplomaApp";
            case RUDI_DOC_DEGREE_RECOGNITION -> appSuffix = "docDegreeApp";
        }

        if (Objects.isNull(trainingCourse)) {
            trainingCourse = new TrainingCourseDTO();
        }

        UniversityDTO university = trainingCourse.getBaseUniversity();
        education.addConstraint(new InitialConstraintDTO.Constraint(Objects.nonNull(university) && Objects.nonNull(university.getId()), "t.base.university.details." + appSuffix));

        List<TrainingLocationDTO> trainingLocations = trainingCourse.getTrainingLocations();
        education.addConstraint(new InitialConstraintDTO.Constraint(!CollectionUtils.isEmpty(trainingLocations), "l.trainingLocations"));

        switch (type) {
            case RUDI_SAR, RUDI_UNI_DIPLOMA_RECOGNITION -> {
                education.addConstraint(new InitialConstraintDTO.Constraint(Objects.nonNull(trainingCourse.getTrainingStart()), "l.trainingStart"));
                education.addConstraint(new InitialConstraintDTO.Constraint(Objects.nonNull(trainingCourse.getTrainingEnd()), "l.trainingEnd"));
            }
        }

        return education;
    }

}
