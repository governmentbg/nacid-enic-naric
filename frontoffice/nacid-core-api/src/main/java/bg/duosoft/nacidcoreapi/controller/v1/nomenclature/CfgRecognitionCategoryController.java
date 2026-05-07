package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CfgRecognitionCategoryService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgRecognitionCategoryDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 16:34
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CFG_RECOGNITION_CATEGORY)
@RequestMapping("/api/v1/cfg-recognition-category")
public class CfgRecognitionCategoryController {

    private final CfgRecognitionCategoryService cfgRecognitionCategoryService;

    @GetMapping
    public List<CfgRecognitionCategoryDTO> getAll() {
        return cfgRecognitionCategoryService.getRecognitionCategoryConfigs();
    }
}
