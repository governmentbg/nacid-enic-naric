package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.NacidUserAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATIONS)
@RequestMapping("/api/v1/applications")
public class ApplicationsController extends BaseAccessController {

    private final ApplicationsService applicationsService;

    @Override
    public String getEditRole() {
        return SecurityRole.CORE_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @GetMapping(value = "/by-type/{applicationType}/autocomplete-created-user")
    @ApiOperation(value = "Filter records")
    public List<NacidUserAutocompleteDTO> getCreatedUsersAutocomplete(@PathVariable("applicationType") String applicationType) {
        return applicationsService.getCreatedUsersAutocomplete(applicationType);
    }

    @GetMapping(value = "/by-type/{applicationType}/autocomplete-responsible-user")
    @ApiOperation(value = "Filter records")
    public List<NacidUserAutocompleteDTO> getResponsibleUsersAutocomplete(@PathVariable("applicationType") String applicationType) {
        return applicationsService.getResponsibleUsersAutocomplete(applicationType);
    }

    @GetMapping(value = "/by-address-type-and-id/{addressType}/{addressId}")
    @ApiOperation(value = "Select records by address type and ID")
    public List<ApplicationTableViewDTO> getApplicationsByAddressTypeAndId(@PathVariable("addressType") String addressType, @PathVariable("addressId") Integer addressId) {
        List<ApplicationTableViewDTO> applicationsByAddressIdAndType = applicationsService.getApplicationsByAddressIdAndType(addressType, addressId);
        if (CollectionUtils.isEmpty(applicationsByAddressIdAndType)) {
            throw new ResourceNotFoundException();
        }

        return applicationsByAddressIdAndType;
    }

    @GetMapping(value = "/count-by-address-type-and-id/{addressType}/{addressId}")
    @ApiOperation(value = "Select records count by address type and ID")
    public Integer getApplicationsCountByAddressTypeAndId(@PathVariable("addressType") String addressType, @PathVariable("addressId") Integer addressId) {
        return applicationsService.getApplicationsCountByAddressIdAndType(addressType, addressId);
    }

    @GetMapping(value = "/by-person-id/{personId}")
    @ApiOperation(value = "Select records by person ID")
    public List<AppPersonDataDTO> getApplicationsByPersonId(@PathVariable("personId") Integer personId) {
        List<AppPersonDataDTO> applicationsByPersonId = applicationsService.getApplicationsByPersonId(personId);
        if (CollectionUtils.isEmpty(applicationsByPersonId)) {
            throw new ResourceNotFoundException();
        }

        return applicationsByPersonId;
    }

    @GetMapping(value = "/count-by-person-id/{personId}")
    @ApiOperation(value = "Select records count by person ID")
    public Integer getApplicationsCountByPersonId(@PathVariable("personId") Integer personId) {
        return applicationsService.getApplicationsCountByPersonId(personId);
    }

    @GetMapping(value = "/status-by-entry-details")
    @ApiOperation(value = "Get application status code by entry number and entry date")
    public String getStatusCodeByEntryDetails(@RequestParam("entryNumber") String entryNumber, @RequestParam("entryDate") LocalDate entryDate) {
        return applicationsService.getStatusCodeByEntryDetails(entryNumber, entryDate);
    }


    @PostMapping(value = "/update-application-paid-flag")
    public void notifyApplicationPayment(@RequestParam("entryNumber") String entryNumber,
                                         @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate,
                                         @RequestParam("paymentFlag") Integer paymentFlag) {
        applicationsService.updateApplicationPaidFlag(entryNumber, entryDate, paymentFlag);
    }

    @GetMapping(value = "/apn-id-by-entry-details")
    @ApiOperation(value = "Get application id by entry number and entry date")
    public Integer getApnIdByEntryDetails(@RequestParam("entryNumber") String entryNumber, @RequestParam("entryDate") LocalDate entryDate) {
        return applicationsService.getApplicationIdByEntryDetails(entryNumber, entryDate);
    }

    @GetMapping(value = "/apn-base-data-by-entry-details")
    @ApiOperation(value = "Get application base data by entry details")
    public ApplicationBaseDataDTO getApplicationBaseDataByEntryDetails(@RequestParam("entryNumber") String entryNumber, @RequestParam("entryDate") LocalDate entryDate) {
        ApplicationBaseDataDTO applicationBaseData = applicationsService.getApplicationBaseData(entryNumber, entryDate);
        return applicationBaseData;
    }

    @GetMapping(value = "/apn-base-data-by-abdocs-id")
    @ApiOperation("Get application base data by abdocs id")
    public ApplicationBaseDataDTO getApplicationBaseDataByAbdocsId(@RequestParam("abdocsId") Integer abdocsId) {
        return applicationsService.getApplicationBaseDataByAbdocsId(abdocsId);
    }

    @GetMapping(value = "/docflow-number/{applicationId}")
    @ApiOperation(value = "Select doclfow number by application id")
    public String getDocflowNumberByApplicationId(@PathVariable("applicationId") Integer applicationId) {
        ApplicationDTO application = ResponseUtils.notFoundCheck(applicationsService.getApplicationById(applicationId));
        return AbdocsNumbersUtils.buildRegistrationNumber(application.getEntryNumber(), application.getEntryDate());
    }

    @GetMapping(value = "/responsible-user-by-backoffice-number")
    @ApiOperation(value = "Select responsible user by backoffice number")
    public String getApplicationResponsibleUserByBackofficeNumber(@RequestParam("backofficeNumber") String backofficeNumber) {
        return applicationsService.getApplicationResponsibleUserByBackofficeNumber(backofficeNumber);
    }
}
