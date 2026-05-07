package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.RudiApplicationsFilterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationsService {
    List<RudiApplicationsDTO> searchRecords(RudiApplicationsFilterDTO filter);
    List<RudiApplicationsDTO> selectApplicationsByIdsAndSort(List<Integer> ids, String sortColumn, Boolean ascOrder);
    List<RudiApplicationsDTO> selectAllByTypeAndStatus(String ateCode, String aseCode, String apnStatusCode);
    ResponseEntity<byte[]> generateReport(RudiApplicationsFilterDTO filter);

    int getRecordsCount(RudiApplicationsFilterDTO filter);

    List<Integer> selectAllApplicationIdsByCalendarId(Integer calendarId);

    List<RudiApplicationsDTO> selectApplicationsByIds(List<Integer> ids);

    RudiApplicationsDTO selectApplicationsById(Integer id);

}
