package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.controller;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.SchoolSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.SchoolSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school-subjects")
public class SchoolSubjectController {

    private final SchoolSubjectService schoolSubjectService;

    @GetMapping()
    public List<SchoolSubjectDto> getAutocompleteSchoolSubjects(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {

        return this.schoolSubjectService.getAutocompleteSchoolSubjects(name.trim().toLowerCase(), page, pageSize);
    }

}
