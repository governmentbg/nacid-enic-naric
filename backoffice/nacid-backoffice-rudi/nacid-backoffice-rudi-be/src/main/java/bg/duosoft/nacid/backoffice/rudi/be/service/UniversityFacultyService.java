package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;

import java.util.List;

public interface UniversityFacultyService {
    FacultyDTO create(FacultyDTO faculty, UniversityDTO university);

    List<FacultyDTO> selectUniversityFacultiesByUniversityIdAndName(Integer universityId, String facultyName);

    FacultyDTO selectFacultyById(Integer facultyId);

}
