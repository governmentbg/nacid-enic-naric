package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.mapper.common.PersonToApplicantDiplomaNamesMapper;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonManagementService;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICANT_DIPLOMA_NAMES)
@RequestMapping("/api/v1/applicant-diploma-names")
public class ApplicantDiplomaNamesController {

    private final PersonService personService;
    private final PersonToApplicantDiplomaNamesMapper personToApplicantDiplomaNamesMapper;

    @GetMapping("/by-person-id/{id}")
    @ApiOperation("Get by person id")
    public ApplicantDiplomaNamesDTO getById(@PathVariable("id") Integer id) {
        PersonDTO person = personService.selectById(id);
        if (Objects.isNull(person)) {
            throw new ResourceNotFoundException();
        }

        return personToApplicantDiplomaNamesMapper.toApplicantDiplomaNames(person);
    }

}
