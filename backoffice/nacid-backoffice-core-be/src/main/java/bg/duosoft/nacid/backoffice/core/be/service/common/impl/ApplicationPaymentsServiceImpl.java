package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPaymentsService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.validation.common.InsertFeesResponseValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.OfficialNoteKind;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.official_note.OfficialNoteAppDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacid.backoffice.core.data.util.common.PersonUtils;
import bg.duosoft.nacid.backoffice.libserv.client.client.app.OfficialNoteAppClient;
import bg.duosoft.nacid.backoffice.rudi.client.client.app.RudiAppClient;
import bg.duosoft.nacid.payments.client.client.feeinsertion.FeeInsertionClient;
import bg.duosoft.nacid.payments.client.client.liabilities.LiabilitiesClient;
import bg.duosoft.nacid.payments.dto.enums.FeeParamName;
import bg.duosoft.nacid.payments.dto.enums.LiabilityModule;
import bg.duosoft.nacid.payments.dto.enums.LiabilityStatus;
import bg.duosoft.nacid.payments.dto.nomenclatures.ModuleDTO;
import bg.duosoft.nacid.payments.dto.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.payments.dto.payments.*;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListRecordDTO;
import bg.duosoft.nacidservicesclient.client.ServicesBoApiClient;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationPaymentsServiceImpl implements ApplicationPaymentsService {
    private final LiabilitiesClient liabilitiesClient;
    private final ApplicationsService applicationsService;
    private final ServicesBoApiClient servicesBoApiClient;
    private final RudiAppClient rudiAppClient;
    private final OfficialNoteAppClient officialNoteAppClient;
    private final FeeInsertionClient feeInsertionClient;
    private final InsertFeesResponseValidator insertFeesResponseValidator;

    @Override
    public void save(Integer applicationId, LiabilityDetailDTO liabilityDetail) {
        LiabilityDTO liability = initLiability(applicationId, liabilityDetail);
        processLiabilityDetail(liability, liabilityDetail);
        saveLiability(liability);
    }

    @Override
    public void delete(Integer liabilityId, Integer liabilityDetailId) {
        LiabilityDTO liability = liabilitiesClient.selectById(liabilityId);
        liability.getLiabilityDetails().removeIf(r -> r.getId().equals(liabilityDetailId));
        liabilitiesClient.update(liability);
    }

    @Override
    public void insertFees(Integer applicationId) {
        FeeInsertionRequest feeInsertionRequest = initFeeInsertRequest(applicationId);
        FeeInsertionResponse insertFeesResponse = feeInsertionClient.insert(feeInsertionRequest);
        checkInsertFeesResponse(insertFeesResponse);
    }

    private FeeInsertionRequest initFeeInsertRequest(Integer applicationId) {
        ApplicationDTO application = applicationsService.getApplicationById(applicationId);
        FeeInsertionRequest feeInsertionRequest = new FeeInsertionRequest();
        feeInsertionRequest.setCalculationRequest(constructCalculationRequest(application));
        fillFeeInsertionRequest(feeInsertionRequest, application);
        return feeInsertionRequest;
    }

    private void checkInsertFeesResponse(FeeInsertionResponse insertFeesResponse) {
        List<ValidationError> errors = insertFeesResponseValidator.validate(insertFeesResponse);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }
    }

    private void fillFeeInsertionRequest(FeeInsertionRequest feeInsertionRequest, ApplicationDTO application) {
        if (Objects.nonNull(application.getEfilingId())) {
            ApplicationListRecordDTO applicationListRecordDTO = servicesBoApiClient.getApplicationById(application.getEfilingId());
            if (Objects.nonNull(applicationListRecordDTO)) {
                feeInsertionRequest.setFrontOfficeUser(applicationListRecordDTO.getUserCreated());
                feeInsertionRequest.setFrontOfficeReferenceNumber(applicationListRecordDTO.getTempNumber());
            }
        }
        feeInsertionRequest.setBackOfficeReferenceNumber(AbdocsNumbersUtils.buildRegistrationNumber(application.getEntryNumber(), application.getEntryDate()));
        feeInsertionRequest.setDescription(application.getApplicationSubtype().getName());
        feeInsertionRequest.setApplicantNames(constructApplicantNames(application));
    }

    private String constructApplicantNames(ApplicationDTO application) {
        StringBuilder applicantNamesBuilder = new StringBuilder();
        PersonDTO applicant = application.getApplicant();
        String applicantName = PersonUtils.getPersonName(applicant);
        applicantNamesBuilder.append(applicantName);
        if (application.getApplicationSubtype().getId().equals(ApplicationSubType.RUDI_SAR.appSubType())) {
            concatDiplomaOwnerToAppNames(application.getId(), applicantName, applicantNamesBuilder);
        } else {
            if (Objects.nonNull(applicant.getCivilIdType()) && StringUtils.hasText(applicant.getCivilId())){
                applicantNamesBuilder.append(", ").append(applicant.getCivilIdType().getName()).append(" ").append(applicant.getCivilId());
            }
        }
        return applicantNamesBuilder.toString();
    }

    private void concatDiplomaOwnerToAppNames(Integer applicationId, String applicantName, StringBuilder applicantNamesBuilder) {
        RudiApplicationDTO rudiApplicationDTO = rudiAppClient.selectById(applicationId);
        TrainingCourseDTO trainingCourse = rudiApplicationDTO.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            PersonDTO diplomaOwner = trainingCourse.getDiplomaOwner();
            if (Objects.nonNull(diplomaOwner)) {
                String diplomaOwnerName = PersonUtils.getPersonName(diplomaOwner);
                if (StringUtils.hasText(diplomaOwnerName) && StringUtils.hasText(applicantName) && !diplomaOwnerName.equals(applicantName)) {
                    applicantNamesBuilder.append(", ").append(diplomaOwnerName);
                }
            }
        }
    }

    private FeeCalculationRequest constructCalculationRequest(ApplicationDTO application) {
        FeeCalculationRequest calculationRequest = new FeeCalculationRequest();
        calculationRequest.setModule(getModuleOnFeeInsert(application.getApplicationType().getId()));
        calculationRequest.setParams(getParamsOnFeeInsert(calculationRequest.getModule(), application));
        return calculationRequest;
    }

    private List<FeeCalculationRequest.FeeCalculationParam> getParamsOnFeeInsert(String module, ApplicationDTO application) {
        List<FeeCalculationRequest.FeeCalculationParam> params = new ArrayList<>();
        if (module.equals(LiabilityModule.RUDI.module())) {
            params.addAll(getRudiFeeInsertParam(application));
        } else if (module.equals(LiabilityModule.REGPROF.module())) {
            params.addAll(getRegprofFeeInsertParam(application));
        } else if (module.equals(LiabilityModule.LIBRARY.module())) {
            params.addAll(getLibservFeeInsertParam(application));
        } else {
            throw new RuntimeException("Module not found!");
        }
        return params;
    }


    private List<FeeCalculationRequest.FeeCalculationParam> getRegprofFeeInsertParam(ApplicationDTO application) {
        List<FeeCalculationRequest.FeeCalculationParam> params = new ArrayList<>();
        bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO serviceType = application.getServiceType();
        if (Objects.nonNull(serviceType)) {
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.SERVICE_TYPE.paramName(), serviceType.getId()));
        }
        return params;
    }

    private List<FeeCalculationRequest.FeeCalculationParam> getLibservFeeInsertParam(ApplicationDTO application) {
        List<FeeCalculationRequest.FeeCalculationParam> params = new ArrayList<>();
        params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.APPLICATION_SUBTYPE.paramName(), application.getApplicationSubtype().getId()));
        if (application.getApplicationSubtype().getId().equals(ApplicationSubType.LIBSERV_OFFICIAL_NOTE.appSubType())) {
            OfficialNoteAppDTO officialNoteAppDTO = officialNoteAppClient.selectById(application.getId());
            bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO kind = officialNoteAppDTO.getKind();
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.DISSERTATION_NOTE_FLAG.paramName(), kind.getId().equals(OfficialNoteKind.DISSERTATION.code()) ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.POSITION_NOTE_FLAG.paramName(), kind.getId().equals(OfficialNoteKind.POSITION.code()) ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
        } else {
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.DISSERTATION_NOTE_FLAG.paramName(), Boolean.FALSE.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.POSITION_NOTE_FLAG.paramName(), Boolean.FALSE.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.PROJECT_NOTE_FLAG.paramName(), Boolean.FALSE.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.PAPER_NOTE_FLAG.paramName(), Boolean.FALSE.toString()));
        }

        return params;
    }

    private List<FeeCalculationRequest.FeeCalculationParam> getRudiFeeInsertParam(ApplicationDTO application) {
        RudiApplicationDTO rudiApplication = rudiAppClient.selectById(application.getId());
        List<FeeCalculationRequest.FeeCalculationParam> params = new ArrayList<>();
        params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.APPLICATION_SUBTYPE.paramName(), application.getApplicationSubtype().getId()));
        params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.LEGAL_TYPE.paramName(), application.getApplicant().getLegalType().getId()));

        bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO legalNatureType = application.getApplicant().getLegalNatureType();
        if (Objects.nonNull(application.getApplicant().getLegalNatureType())) {
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.LEGAL_NATURE_TYPE.paramName(), legalNatureType.getId()));
        }

        SarApplicationDTO sarApplication = rudiApplication.getSarApplication();
        if (Objects.nonNull(sarApplication)) {
            Boolean isStatute = sarApplication.getIsStatute();
            Boolean isAuthenticity = sarApplication.getIsAuthenticity();
            Boolean isRecommendation = sarApplication.getIsRecommendation();
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.STATUTE_FLAG.paramName(), Objects.isNull(isStatute) ? Boolean.FALSE.toString() : isStatute.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.AUTHENTICITY_FLAG.paramName(), Objects.isNull(isAuthenticity) ? Boolean.FALSE.toString() : isAuthenticity.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.RECOMMENDATION_FLAG.paramName(), Objects.isNull(isRecommendation) ? Boolean.FALSE.toString() : isRecommendation.toString()));
        } else {
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.STATUTE_FLAG.paramName(), Boolean.FALSE.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.AUTHENTICITY_FLAG.paramName(), Boolean.FALSE.toString()));
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.RECOMMENDATION_FLAG.paramName(), Boolean.FALSE.toString()));
        }

        bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO serviceType = application.getServiceType();
        if (Objects.nonNull(serviceType)) {
            params.add(new FeeCalculationRequest.FeeCalculationParam(FeeParamName.SERVICE_TYPE.paramName(), serviceType.getId()));
        }

        return params;
    }

    private String getModuleOnFeeInsert(String appType) {
        if (!StringUtils.hasText(appType)) {
            throw new RuntimeException("Empty application type on module search!");
        }
        ApplicationType applicationType = ApplicationType.selectByCode(appType);
        switch (applicationType) {
            case RUDI -> {
                return LiabilityModule.RUDI.module();
            }
            case REGPROF -> {
                return LiabilityModule.REGPROF.module();
            }
            case LIBSERV -> {
                return LiabilityModule.LIBRARY.module();
            }
            default -> {
                throw new RuntimeException("Module not found for application type: " + appType);
            }
        }
    }

    private void saveLiability(LiabilityDTO liability) {
        if (Objects.nonNull(liability.getId())) {
            liabilitiesClient.update(liability);
        } else {
            liabilitiesClient.create(liability);
        }
    }

    private void processLiabilityDetail(LiabilityDTO liability, LiabilityDetailDTO liabilityDetail) {
        List<LiabilityDetailDTO> liabilityDetails = liability.getLiabilityDetails();
        if (Objects.isNull(liabilityDetail.getId())) {
            liabilityDetail.setDateCreated(LocalDateTime.now());
            liabilityDetails.add(liabilityDetail);
        } else {
            LiabilityDetailDTO existedLiabilityDetail = liabilityDetails.stream().filter(r -> r.getId().equals(liabilityDetail.getId())).findFirst().orElse(null);
            int existedLiabilityDetailIndex = liabilityDetails.indexOf(existedLiabilityDetail);
            liabilityDetails.set(existedLiabilityDetailIndex, liabilityDetail);
        }
    }

    private LiabilityDTO initLiability(Integer applicationId, LiabilityDetailDTO liabilityDetail) {
        LiabilityDTO liability = null;
        if (Objects.nonNull(liabilityDetail.getLiabilityId())) {
            liability = liabilitiesClient.selectById(liabilityDetail.getLiabilityId());
        } else {
            ApplicationDTO application = applicationsService.getApplicationById(applicationId);
            liability = new LiabilityDTO();
            liability.setModule(getLiabilityModule(application));
            if (Objects.nonNull(application.getEfilingId())) {
                ApplicationListRecordDTO applicationListRecordDTO = servicesBoApiClient.getApplicationById(application.getEfilingId());
                if (Objects.nonNull(applicationListRecordDTO)) {
                    liability.setFrontOfficeUser(applicationListRecordDTO.getUserCreated());
                    liability.setFrontOfficeReferenceNumber(applicationListRecordDTO.getTempNumber());
                }
            }
            liability.setApplicantNames(constructApplicantNames(application));
            liability.setBackOfficeReferenceNumber(AbdocsNumbersUtils.buildRegistrationNumber(application.getEntryNumber(), application.getEntryDate()));
            liability.setDateCreated(LocalDateTime.now());
            ReferenceDataDTO status = new ReferenceDataDTO();
            status.setId(LiabilityStatus.ACTIVE.status());
            liability.setStatus(status);
            liability.setDescription(application.getApplicationSubtype().getName());
        }

        if (CollectionUtils.isEmpty(liability.getLiabilityDetails())) {
            liability.setLiabilityDetails(new ArrayList<>());
        }
        return liability;
    }


    private ModuleDTO getLiabilityModule(ApplicationDTO application) {
        ApplicationType type = ApplicationType.selectByCode(application.getApplicationType().getId());
        return switch (type) {
            case RUDI -> new ModuleDTO(LiabilityModule.RUDI.module());
            case REGPROF -> new ModuleDTO(LiabilityModule.REGPROF.module());
            case LIBSERV -> new ModuleDTO(LiabilityModule.LIBRARY.module());
        };
    }
}
