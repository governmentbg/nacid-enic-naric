package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.PersonUniversityAdditionalDetailsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonUniversityAdditionalDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.PERSON_UNIVERSITY_ADDITIONAL_DETAILS)
@RequestMapping("/api/v1/person-university-additional-details")
public class PersonUniversityAdditionalDetailsController extends BaseAccessController {

    private final PersonUniversityAdditionalDetailsService service;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @GetMapping({"/{id}"})
    @ApiOperation("Select person university additional details by id")
    public PersonUniversityAdditionalDetailsDTO getById(@PathVariable("id") Integer id) {
        return service.selectById(id);
    }

    @PutMapping
    @ApiOperation(value = "Save person university additional details")
    public PersonUniversityAdditionalDetailsDTO save(@RequestBody PersonUniversityAdditionalDetailsDTO requestData) {
        return service.process(requestData);
    }

}
