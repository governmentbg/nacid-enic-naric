package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.fo.FoAppService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.PersonRole;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.FoApplicationDataConverter;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.FoNaturalPersonMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.PersonUtils;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ChangeFoApplicationStatusRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatusChangeType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RevertApplicationStatusToDraftRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesclient.client.ServicesBoApiClient;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static bg.duosoft.nacidshareddata.util.ResponseUtils.notFoundCheck;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.FO_APPLICATIONS)
@RequestMapping("/api/v1/fo-applications")
public class FoApplicationController {

    private final FoApplicationDataConverter foApplicationDataConverter;
    private final ServicesBoApiClient servicesBoApiClient;
    private final ApplicationsService applicationsService;
    private final FoAppService foAppService;
    private final FoNaturalPersonMapper foNaturalPersonMapper;


    @GetMapping(value = "/{applicationId}")
    @ApiOperation(value = "Select fo application by bo application id")
    public CommonApplicationDTO selectFoApplicationByBoAppId(@PathVariable Integer applicationId) {
        ApplicationDTO applicationDTO = notFoundCheck(applicationsService.getApplicationById(applicationId));
        Integer efilingId = notFoundCheck(applicationDTO.getEfilingId());
        return foAppService.selectFoApplication(efilingId, applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/status-revert/{efilingId}")
    @ApiOperation(value = "Revert front-office status")
    public void revertFoStatus(@PathVariable Integer efilingId, @RequestBody RevertFoStatusDTO revertFoStatusDTO) {
        RevertApplicationStatusToDraftRequestDTO requestDTO = new RevertApplicationStatusToDraftRequestDTO();
        requestDTO.setApplicationId(efilingId);
        requestDTO.setRevertMessage(revertFoStatusDTO.getMessage());
        requestDTO.setInitiatingUser(SecurityUtils.getUsername());
        servicesBoApiClient.revertApplicationToDraft(requestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/status-change/{changeType}/{efilingId}")
    @ApiOperation(value = "Change front-office status")
    public void changeFoStatus(@PathVariable Integer efilingId, @PathVariable FoApplicationStatusChangeType changeType) {
        ChangeFoApplicationStatusRequestDTO request = new ChangeFoApplicationStatusRequestDTO();
        request.setApplicationId(efilingId);
        request.setStatusChangeType(changeType);
        request.setInitiatingUser(SecurityUtils.getUsername());
        servicesBoApiClient.simplyChangeApplicationStatus(request);
    }

    @GetMapping(value = "/{applicationId}/exists")
    @ApiOperation(value = "Checks if application is e-filled")
    public IntegerIdDTO isElectronicallyFilled(@PathVariable Integer applicationId) {
        Integer efilingId = notFoundCheck(applicationsService.selectEfilingIdByApplicationId(applicationId));
        return new IntegerIdDTO(efilingId);
    }

    @GetMapping(value = "/efilingId/{id}/{appType}/{appSubType}")
    @ApiOperation(value = "Select fo application by efiling id")
    public CommonApplicationDTO selecFoApplicationByEfilingId(@PathVariable Integer id, @PathVariable String appType, @PathVariable String appSubType) {
        return foAppService.selectFoApplication(id, appType, appSubType);
    }

    @GetMapping("/extraction/persons/{foAppId}/{appType}/{appSubType}/{personRole}")
    @ApiOperation(value = "Select person from front-office application")
    public PersonDTO selectFoPersonByRole(@PathVariable Integer foAppId, @PathVariable String appType, @PathVariable String appSubType, @PathVariable PersonRole personRole) {
        CommonApplicationDTO foApplication = ResponseUtils.notFoundCheck(foAppService.selectFoApplication(foAppId, appType, appSubType));

        if (personRole == PersonRole.DIPLOMA_OWNER) {
            ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(appType, appSubType);
            if (type == ApplicationSubType.RUDI_SAR && foApplication instanceof UniChecksApplicationDTO sarApp) {
                UniChecksEducationDetailsDTO educationDetails = sarApp.getEducationDetails();
                if (Objects.nonNull(educationDetails)) {
                    PersonDTO diplomaOwner = foNaturalPersonMapper.toNaturalPersonDto(educationDetails.getDiplomaHolder());
                    return ResponseUtils.notFoundCheck(diplomaOwner);
                }
            }
        }

        ApplicationDTO boApplication = new ApplicationDTO();
        foApplicationDataConverter.setCommonApplicationData(boApplication, foApplication);
        PersonDTO person = PersonUtils.extractPersonByRole(personRole, boApplication);
        return ResponseUtils.notFoundCheck(person);
    }

    @GetMapping("/extraction/addresses/{foAppId}/{appType}/{appSubType}/{addressTypeCode}")
    @ApiOperation(value = "Select address from front-office application")
    public AddressDTO selectFoAddressByType(@PathVariable Integer foAppId, @PathVariable String appType, @PathVariable String appSubType, @PathVariable String addressTypeCode) {
        AddressType addressType = AddressType.selectByCode(addressTypeCode);
        CommonApplicationDTO foApplication = ResponseUtils.notFoundCheck(foAppService.selectFoApplication(foAppId, appType, appSubType));

        ApplicationDTO boApplication = new ApplicationDTO();
        foApplicationDataConverter.setCommonApplicationData(boApplication, foApplication);
        AddressDTO address = extractAddress(addressType, boApplication);
        return ResponseUtils.notFoundCheck(address);
    }


    private AddressDTO extractAddress(AddressType addressType, ApplicationDTO application) {


        return switch (addressType) {
            case CONTACT -> application.getContactAddress();
            case DOCUMENT -> {
                List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods = application.getDocumentReceiveMethods();
                if (CollectionUtils.isEmpty(documentReceiveMethods)) {
                    yield null;
                } else {
                    ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethod = documentReceiveMethods.stream().filter(r -> Objects.nonNull(r.getDocumentRecipientAddress())).findFirst().orElse(null);
                    yield Objects.nonNull(applicationDocumentReceiveMethod.getDocumentRecipientAddress()) ? applicationDocumentReceiveMethod.getDocumentRecipientAddress() : null;
                }
            }
            default -> null;
        };
    }
}
