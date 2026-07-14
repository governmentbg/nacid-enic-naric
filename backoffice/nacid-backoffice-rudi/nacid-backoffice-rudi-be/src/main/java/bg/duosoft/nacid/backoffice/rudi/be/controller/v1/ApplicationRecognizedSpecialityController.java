package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationRecognizedSpecialityService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APP_RECOGNIZED_SPECIALITIES)
@RequestMapping("/api/v1/application-recognized-specialities")
public class ApplicationRecognizedSpecialityController {


    private final ApplicationRecognizedSpecialityService applicationRecognizedSpecialityService;

    @GetMapping
    @ApiOperation("Select all specialities")
    public List<String> getAll() {
        return applicationRecognizedSpecialityService.selectAllDistinctSpecialities();
    }
}
