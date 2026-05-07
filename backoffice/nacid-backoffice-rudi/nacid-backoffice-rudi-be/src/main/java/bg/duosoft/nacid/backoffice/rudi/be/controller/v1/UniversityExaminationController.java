package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.UniversityExaminationSimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityExaminationService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.CrudController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.UNIVERSITY_EXAMINATION)
@RequestMapping("/api/v1/university-examination")
public class UniversityExaminationController extends CrudController<Integer, TrainingCourseUniversityExaminationDTO> {
    private final UniversityExaminationService universityExaminationService;

    @Override
    public String getEditRole() {
        return SecurityRole.RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.RUDI_APPLICATION_ACCESS;
    }

    @Override
    protected UniversityExaminationService getService() {
        return universityExaminationService;
    }

    @GetMapping(value = "/by-university/{id}")
    @ApiOperation(value = "Get university examinations by university")
    public List<UniversityExaminationSimpleDTO> selectUniversityExaminationByUniversity(@PathVariable("id") Integer id) {
            List<TrainingCourseUniversityExaminationDTO> universityExaminations = universityExaminationService.selectUniversityExaminationsByUniversity(id);
            return universityExaminations.stream().map(ex -> UniversityExaminationSimpleDTO.newInstance(ex.getId(), ex.getExaminationDate(), ex.getIsRecognized(), ex.getNotes())).collect(Collectors.toList());
    }
}
