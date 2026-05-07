package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ProfessionExperienceDocumentTypeService;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfessionExperienceDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfessionExperienceDocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_PROF_EXPERIENCE_DOCUMENT_TYPE)
@RequestMapping("/api/v1/profession-experience-document-type")
public class ProfessionExperienceDocumentTypeController extends NomenclatureBaseController<String, ProfessionExperienceDocumentTypeDTO, ProfessionExperienceDocumentTypeFilterDTO> {

    private final ProfessionExperienceDocumentTypeService service;

    @Override
    protected NomenclatureServiceBase<String, ProfessionExperienceDocumentTypeDTO, ProfessionExperienceDocumentTypeFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

}
