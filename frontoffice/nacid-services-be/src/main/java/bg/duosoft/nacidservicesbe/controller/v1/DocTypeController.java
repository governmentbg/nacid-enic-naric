package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import bg.duosoft.nacidservicesbe.service.ServiceHelper;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 16:52
 */
@Api(tags = Tags.BO_API)
@RestController
@RequestMapping("/api/v1/doc-type")
@RequiredArgsConstructor
public class DocTypeController {

    private final ServiceHelper serviceHelper;
    private final DocTypeService docTypeService;

    @GetMapping("/app-doc-types")
    public List<DocTypeDTO> getDocTypesForApplication(Integer id){
        CommonApplicationDTO application = serviceHelper.getSpecificApplicationService(id).getApplication(id);
        return docTypeService.getApplicationDocTypes(application);
    }
}
