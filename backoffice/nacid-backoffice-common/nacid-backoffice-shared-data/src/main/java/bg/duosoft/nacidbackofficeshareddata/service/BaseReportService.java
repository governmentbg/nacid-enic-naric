package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;

import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.09.2023
 * Time: 18:00
 */
public interface BaseReportService<D, F extends BaseFilterDTO> {
    List<D> getReportApplications(F filter);
    List<Integer> getReportApplicationIds(F filter);
    int getReportApplicationsCount(F filter);
}
