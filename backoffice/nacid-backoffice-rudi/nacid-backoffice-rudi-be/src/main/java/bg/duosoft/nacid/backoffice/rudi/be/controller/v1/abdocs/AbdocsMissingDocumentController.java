package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.abdocs;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.rudi.be.service.AbdocsMissingDocumentService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.ABDOCS_DOCS)
@RequestMapping("/api/v1/abdocs/missing-doc")
public class AbdocsMissingDocumentController {

    private final AbdocsMissingDocumentService abdocsMissingDocumentService;

    @PostMapping
    public Doc addMissingAbdocsDocument(@RequestParam Integer applicationId) {
        return abdocsMissingDocumentService.generateDocument(applicationId);
    }
}
