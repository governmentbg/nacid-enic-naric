package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.DocumentTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.Direction;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * User: ggeorgiev
 * Date: 19.08.2022
 * Time: 13:33
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_DOCUMENT_TYPE)
@RequestMapping("/api/v1/document-types")
public class DocumentTypeController extends NomenclatureBaseController<Integer, DocumentTypeDTO, DocumentTypeFilterDTO> {

    private final DocumentTypeService service;
    private final DocumentTypeService documentTypeService;

    @Override
    protected NomenclatureServiceBase<Integer, DocumentTypeDTO, DocumentTypeFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(value = "/by-application-type/{applicationType}")
    @ApiOperation(value = "Select document types by application type")
    public List<DocumentTypeDTO> getAllByApplicationType(@PathVariable("applicationType") String applicationType, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return service.selectByApplicationType(applicationType, onlyActive);
    }


    @GetMapping(value = "/applications/{applicationId}/{docCategory}")
    @ApiOperation(value = "select all by application id, direction and doc category")
    public List<DocumentTypeDTO> getAllByDocCategoryAndAppId(@PathVariable("applicationId") Integer applicationId, @PathVariable("docCategory") String docCategory,
                                                             @RequestParam(value = "direction", required = false) String direction,
                                                             @RequestParam(value = "selectedDocumentTypeId", required = false) Integer selectedDocumentTypeId,
                                                             @RequestParam(value = "finalized", required = false) Boolean finalized) {

        List<DocumentTypeDTO> documentTypes = documentTypeService.selectDocumentTypes(selectedDocumentTypeId, applicationId, DocCategory.selectByCode(docCategory), !StringUtils.hasText(direction) ? null : Direction.selectByCode(direction), true, finalized);
        return documentTypes;
    }


    @GetMapping(value = "/finalized-check")
    @ApiOperation(value = "Check if document is finalized")
    public Boolean documentTypeFinalizedCheck(@RequestParam("docTypeId") Integer docTypeId,
                                          @RequestParam("docCategory") String docCategory,
                                          @RequestParam("applicationId") Integer applicationId) {
        DocumentTypeDTO documentTypeDTO = service.selectDocumentType(docTypeId,DocCategory.selectByCode(docCategory),applicationId);
        if(Objects.isNull(documentTypeDTO)){
            return false;
        }
        return service.filterDocumentTypeByFinalizedCondition(documentTypeDTO,true);
    }

}