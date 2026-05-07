package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceListFilterDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsDocDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.AccessUtils;
import bg.duosoft.nacidservicesbe.service.ApplicationCorrespondenceService;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesAdminService;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.common.correspondence.ApplicationCorrespondenceListFilterValidator;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.UnauthorizedException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2023
 * Time: 11:57
 */
@Api(tags = Tags.APP_CORRESPONDENCE)
@RestController
@RequestMapping("/api/v1/my-correspondence")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class MyCorrespondenceController {

    private final ApplicationCorrespondenceService applicationCorrespondenceService;
    private final ApplicationCorrespondenceListFilterValidator applicationCorrespondenceListFilterValidator;

    private final CommonApplicationService commonApplicationService;
    private final BoPublicServicesAdminService boPublicServicesAdminService;

    @PostMapping("/filter")
    public Page<ApplicationCorrespondenceDTO> filterApplicationCorrespondence(@RequestBody ApplicationCorrespondenceListFilterDTO listFilter){
        BadRequestValidator.validateRequest(applicationCorrespondenceListFilterValidator, listFilter);
        String username = SecurityUtils.getUsername();
        if(StringUtils.hasText(username)) {
            listFilter.setUser(username);
            List<ApplicationCorrespondenceDTO> resultList = applicationCorrespondenceService.filterAllCorrespondence(listFilter);
            Integer total = applicationCorrespondenceService.getTotalCorrespondenceCount(listFilter);
            return new Page<>(total, resultList, listFilter.getPageSize());
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/for-application/{applicationId}")
    public List<ApplicationCorrespondenceDTO> getApplicationCorrespondenceForApplicationId(@PathVariable Integer applicationId){
        AccessUtils.checkAccessAllowedForAppView(applicationId, commonApplicationService);
        return applicationCorrespondenceService.getCorrespondenceForApplication(applicationId);
    }

    @PostMapping("/read/{applicationId}/{id}")
    public ApplicationCorrespondenceDTO readApplicationCorrespondence(@PathVariable Integer applicationId, @PathVariable Integer id){
        AccessUtils.checkAccessAllowedForAppModification(applicationId, commonApplicationService);
        ApplicationCorrespondenceDTO correspondence = applicationCorrespondenceService.getCorrespondence(id);
        if(!correspondence.getApplicationId().equals(applicationId)){
            throw new BadRequestException();
        }
        return applicationCorrespondenceService.readCorrespondence(id);
    }

    @GetMapping("/details/{applicationId}/{id}")
    public DmsDocDetailsDTO getCorrespondenceDetails(@PathVariable Integer applicationId, @PathVariable Integer id){
        AccessUtils.checkAccessAllowedForAppView(applicationId, commonApplicationService);
        ApplicationCorrespondenceDTO correspondence = applicationCorrespondenceService.getCorrespondence(id);
        if(correspondence.getDateRead() == null || !correspondence.getApplicationId().equals(applicationId)){
            throw new BadRequestException();
        }
        return boPublicServicesAdminService.getDmsDocDetailsForBoAttachedDocId(correspondence.getRefId());
    }
}
