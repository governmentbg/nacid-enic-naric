package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidservicesbe.service.ApplicationCorrespondenceService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.common.correspondence.ApplicationCorrespondenceValidator;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 16:56
 */
@Api(tags = Tags.APP_CORRESPONDENCE)
@RestController
@RequestMapping("/api/v1/app-correspondence")
@RequiredArgsConstructor
@PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).CORRESPONDENCE_CREATE)")
public class ApplicationCorrespondenceController {

    private final ApplicationCorrespondenceService applicationCorrespondenceService;
    private final ApplicationCorrespondenceValidator applicationCorrespondenceValidator;

    @PostMapping("/create")
    public ApplicationCorrespondenceDTO createApplicationCorrespondence(@RequestBody ApplicationCorrespondenceDTO applicationCorrespondenceDTO){
        BadRequestValidator.validateRequest(applicationCorrespondenceValidator, applicationCorrespondenceDTO);
        applicationCorrespondenceDTO.setDateCreated(LocalDateTime.now());
        return applicationCorrespondenceService.createCorrespondence(applicationCorrespondenceDTO);
    }
}
