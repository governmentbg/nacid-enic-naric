package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.SchoolSubjectDto;

import java.util.List;

public interface SchoolSubjectService {

    List<SchoolSubjectDto> getAutocompleteSchoolSubjects(String name, Integer page, Integer pageSize);

    void saveNonExistSchoolSubjects(List<DiplomaSubjectDto> diplomaSubjects);
}
