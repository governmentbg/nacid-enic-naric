package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseReportRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseReportRepositoryImpl;
import bg.duosoft.nacidbackofficeshareddata.service.BaseReportService;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.09.2023
 * Time: 18:01
 */
public abstract class BaseReportServiceImpl<D, F extends BaseFilterDTO> implements BaseReportService<D, F> {
    protected abstract <E extends Serializable> BaseObjectMapper<E, D> getMapper();
    protected abstract <E extends Serializable> BaseReportRepository<E, F> getRepository();
    @Override
    public List<D> getReportApplications(F filter) {
        return getMapper().toDtoList(getRepository().getReportApplications(filter));
    }

    @Override
    public List<Integer> getReportApplicationIds(F filter) {
        return getRepository().getReportApplicationIds(filter);
    }

    @Override
    public int getReportApplicationsCount(F filter) {
        return getRepository().getReportApplicationsCount(filter);
    }
}
