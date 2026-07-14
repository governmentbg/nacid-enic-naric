package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;


import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.DocumentTypeDetailsService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.DocumentTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailsFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_DOCUMENT_TYPE_DETAILS)
@RequestMapping("/api/v1/document-type-details")
public class DocumentTypeDetailsController extends BaseAccessController {
    private final DocumentTypeDetailsService documentTypeDetailsService;

    private final ApplicationsService applicationsService;
    private final DocumentTypeService documentTypeService;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }


    @GetMapping
    public List<DocumentTypeDetailDTO> selectDocumentTypeDetails(DocumentTypeDetailsFilterDTO filter) {
        if (Objects.isNull(filter.getDocType())) {
            return null;
        }
        if (Objects.nonNull(filter.getApplicationId())) {
            DocumentTypeDTO dt = documentTypeService.selectDocumentType(filter.getDocType(), DocCategory.selectByCode(filter.getDocCategory()), filter.getApplicationId());
            return dt == null ? null : dt.getDetails();
        }
        return documentTypeDetailsService.selectDocumentTypeDetails(filter.getDocType(), filter.getDocCategory(), filter.getApplicationType(), filter.getApplicationSubType());
    }

    @GetMapping("/by-limit-params")
    public List<DocumentTypeDetailDTO> selectDocumentTypeDetails(@RequestParam(value = "applicationId") Integer applicationId,
                                                                 @RequestParam(value = "docCategory") String docCategory, @RequestParam(value = "docType") Integer docType) {
        DocumentTypeDetailsFilterDTO filter = new DocumentTypeDetailsFilterDTO();
        filter.setApplicationId(applicationId);
        filter.setDocCategory(docCategory);
        filter.setDocType(docType);
        return selectDocumentTypeDetails(filter);
    }
}
