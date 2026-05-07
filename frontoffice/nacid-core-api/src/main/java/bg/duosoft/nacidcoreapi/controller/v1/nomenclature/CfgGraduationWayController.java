package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CfgGraduationWayService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgGraduationWayDTO;
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
 * Time: 14:19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CFG_GRADUATION_WAY)
@RequestMapping("/api/v1/cfg-graduation-way")
public class CfgGraduationWayController {

    private final CfgGraduationWayService cfgGraduationWayService;

    @GetMapping
    public List<CfgGraduationWayDTO> getAll() {
        return cfgGraduationWayService.getGraduationWaysConfigs();
    }
}
