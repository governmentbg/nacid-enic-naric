package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;

import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.09.2023
 * Time: 17:59
 */
public interface BaseReportRepository<E extends Serializable, F extends BaseFilterDTO> {
    public List<E> getReportApplications(F filter);
    List<Integer> getReportApplicationIds(F filter);
    public int getReportApplicationsCount(F filter);
}
