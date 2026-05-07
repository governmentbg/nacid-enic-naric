package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.UniversityFilterDTO;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;

import java.util.List;

public interface UniversityService extends CrudServiceBase<Integer, UniversityDTO> {
    List<UniversityDTO> searchRecords(UniversityFilterDTO filter);

    int getRecordsCount(UniversityFilterDTO filter);

    List<UniversityDTO> selectByCountry(String countryCode);

    void toggleActivation(Integer id);

    public List<Integer> selectBaseUniIdByTrainingCourse(Integer id);

    public List<Integer> selectSecondaryUniIdsByTrainingCourse(Integer id);

    List<FacultyDTO> searchUniFacultiesByName(Integer universityId, String name, Boolean onlyActive, Integer page, Integer pageSize);

    List<FacultyDTO> selectUniversityFacultiesByUniversityId(Integer universityId);

    String selectNameById(Integer id);

    List<UniversityDTO> selectUniversityByBgNameExact(String bgName);

    UniversityDTO selectByIdWithFacultyData(Integer objectId);

}
