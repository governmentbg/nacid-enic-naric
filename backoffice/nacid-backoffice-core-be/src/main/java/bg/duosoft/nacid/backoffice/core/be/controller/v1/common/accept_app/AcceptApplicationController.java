package bg.duosoft.nacid.backoffice.core.be.controller.v1.common.accept_app;

import bg.duosoft.nacid.backoffice.core.be.service.common.accept_app.AcceptApplicationFileService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.FO_APPLICATIONS)
@RequestMapping("/api/v1/applications/acceptance")
public class AcceptApplicationController {

    private final AcceptApplicationFileService acceptApplicationFilesService;

    @PostMapping("/files-processing")
    @ApiOperation(value = "Process files on e-application acceptance")
    public List<AttachedDocDTO> processFiles(@RequestBody ApplicationDTO application) {
        return acceptApplicationFilesService.processFiles(application);
    }

    @PostMapping("/files-processing/doc-delivery-attachment/{foFileNameAndId}")
    @ApiOperation(value = "Process files on e-application acceptance")
    public AttachmentDTO processFiles(@PathVariable String foFileNameAndId, @RequestBody ApplicationDTO application) {
        return acceptApplicationFilesService.processDocDeliveryAttachment(foFileNameAndId, application);
    }
}
