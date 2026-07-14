package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.PersonalNacidIdService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonalNacidIdDTO;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/personal-nacid-id")
@RequiredArgsConstructor
public class PersonalNacidIdController {

    private final PersonalNacidIdService personalNacidIdService;

    @PostMapping("/back-office")
    public PersonalNacidIdDTO generateForBackOffice() {
        return personalNacidIdService.generateAndSave(SecurityUtils.getUsername());
    }

    @PostMapping("/front-office")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).PUBLIC_SERVICES_ACCESS)")
    public PersonalNacidIdDTO generateForFrontOffice(@RequestParam String userGenerated) {
        return personalNacidIdService.generateAndSave(userGenerated);
    }

}
