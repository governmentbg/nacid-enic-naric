package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationCertificateService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CORRESPONDENCE_DOCS)
@RequestMapping("/api/v1/application-certificates")
public class ApplicationCertificatesController {
    private final ApplicationCertificateService applicationCertificateService;

    @GetMapping("/select-certificates-fo")
    @ApiOperation(value = "Select certificates for FO by efiling id")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).PUBLIC_SERVICES_ACCESS)")
    public ApplicationCertificatesDTO selectCertificatesForFO(@RequestParam Integer efilingId) {
        List<ApplicationCertificatesDTO> applicationCertificates = applicationCertificateService.selectCertificatesForFO(efilingId);
        return CollectionUtils.isEmpty(applicationCertificates) ? null : applicationCertificates.get(0);
    }

}
