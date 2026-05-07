package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.SimilarDiplomaDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.RudiApplicationsFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationsService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATIONS)
@RequestMapping("/api/v1/applications")
public class AppController extends BaseAccessController {
    private final ApplicationsService vwApplicationsService;
    private final RudiApplicationService rudiApplicationService;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "Filter view records")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).RUDI_APPLICATION_ACCESS)")
    public Page<RudiApplicationsDTO> searchData(@RequestBody RudiApplicationsFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<RudiApplicationsDTO> results = vwApplicationsService.searchRecords(filter);
        return new Page<>(vwApplicationsService.getRecordsCount(filter), results, filter.getPageSize());
    }

    @PostMapping(value = "/report-generation")
    @ApiOperation(value = "Generate applications report")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).RUDI_APPLICATION_ACCESS)")
    public ResponseEntity<byte[]> generateReport(@RequestBody RudiApplicationsFilterDTO filter) {
        return vwApplicationsService.generateReport(filter);
    }

    @GetMapping(value = "/search/{id}")
    @ApiOperation(value = "Select view record by id")
    public RudiApplicationsDTO selectViewRecordById(@PathVariable Integer id) {
        RudiApplicationsDTO result = vwApplicationsService.selectApplicationsById(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select rudi application by id")
    public RudiApplicationDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO rudiApplicationDTO = rudiApplicationService.selectById(id);
        if (Objects.isNull(rudiApplicationDTO)) {
            throw new ResourceNotFoundException();
        }
        return rudiApplicationDTO;
    }

    @GetMapping(value = "/{id}/exists/{appSubType}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Check if rudi application exists")
    public void checkIfExists(@PathVariable Integer id, @PathVariable String appSubType) {
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(ApplicationType.RUDI.code(), appSubType);
        boolean exists = rudiApplicationService.existsByIdAndType(id, type);
        if (!exists) {
            throw new ResourceNotFoundException();
        }
    }


    @GetMapping(value = "/similar-diplomas")
    @ApiOperation(value = "Select similar diplomas")
    public List<SimilarDiplomaDTO> selectSimilarDiplomas(@RequestParam(required = false, defaultValue = "0") Integer applicationId,
                                                         @RequestParam(required = false) LocalDate diplomaDate,
                                                         @RequestParam(required = false) String countryName,
                                                         @RequestParam(required = false) String eduLevel,
                                                         @RequestParam(required = false) String originalEduLevel,
                                                         @RequestParam(required = false) String civilId,
                                                         @RequestParam(required = false) String ownerFirstName,
                                                         @RequestParam(required = false) String ownerLastName,
                                                         @RequestParam(required = false) LocalDate birthDate,
                                                         @RequestParam(required = false) String birthCountry,
                                                         @RequestParam(required = false) String diplomaOwnerEan
    ) {
        List<RudiApplicationDTO> applications = rudiApplicationService.selectAppsWithSimilarDiplomasById(applicationId, Objects.nonNull(diplomaDate) ? diplomaDate.getYear() : null, countryName, eduLevel, originalEduLevel, civilId, ownerFirstName,ownerLastName,birthDate,birthCountry, diplomaOwnerEan);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (!CollectionUtils.isEmpty(applications)) {
            List<SimilarDiplomaDTO> similarDiplomaDTOS = applications.stream().map(x ->
                    new SimilarDiplomaDTO(
                            x.getApplication().getId(),
                            x.getApplication().getEntryNumber() + " / " + formatter.format(x.getApplication().getEntryDate()),
                            x.getTrainingCourse().getDiplomaOwner().getFirstName(),
                            x.getTrainingCourse().getDiplomaOwner().getMiddleName(),
                            x.getTrainingCourse().getDiplomaOwner().getLastName(),
                            x.getTrainingCourse().getDiplomaOwner().getCivilId(),
                            x.getTrainingCourse().getDiplomaOwnerEan(),
                            Objects.nonNull(x.getTrainingCourse().getBaseUniversity()) ? x.getTrainingCourse().getBaseUniversity().getCountry().getName() : null,
                            Objects.nonNull(x.getTrainingCourse().getBaseUniversity()) ? x.getTrainingCourse().getBaseUniversity().getBgName() : null,
                            x.getTrainingCourse().getOriginalEduLevelName(),
                            x.getTrainingCourse().getOriginalEduLevelTranslated(),
                            x.getTrainingCourse().getTrainingCourseSpecialities(),
                            Objects.nonNull(x.getTrainingCourse().getDiplomaDate()) ? x.getTrainingCourse().getDiplomaDate().getYear() : null,
                            x.getApplication().getApplicationSubtype().getId()
                    )).toList();
            return similarDiplomaDTOS;
        } else {
            return null;
        }
    }

}
