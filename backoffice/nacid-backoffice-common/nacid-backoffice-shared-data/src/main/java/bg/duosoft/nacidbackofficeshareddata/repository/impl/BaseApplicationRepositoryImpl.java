package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseApplicationRepository;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class BaseApplicationRepositoryImpl extends BaseRepositoryCustomImpl implements BaseApplicationRepository {

    @Override
    public ApplicationEntity selectApplicationById(Integer applicationId) {
        String queryString = "SELECT r FROM ApplicationEntity r WHERE r.id = :applicationId ";
        TypedQuery<ApplicationEntity> query = em.createQuery(queryString, ApplicationEntity.class);
        query.setParameter("applicationId", applicationId);
        List<ApplicationEntity> resultList = query.getResultList();

        if (CollectionUtils.isEmpty(resultList)) {
            throw new ResourceNotFoundException("Application not found ! ID: " + applicationId);
        }

        return resultList.get(0);
    }

    public LocalDate getExecutionPeriodEnd(Integer applicationId) {
        Query query = em.createNativeQuery("SELECT common.calculate_execution_period_end(:id)");
        query.setParameter("id", applicationId);
        Date result = (Date) query.getSingleResult();

        return DateUtils.convertToLocalDate(result);
    }

    @Override
    public ApplicationEntity selectApplicationByEntryNumberAndDate(String entryNumber, LocalDate entryDate) {
        String queryString = "SELECT r FROM ApplicationEntity r WHERE r.entryNumber = :entryNumber and r.entryDate = :entryDate ";
        TypedQuery<ApplicationEntity> query = em.createQuery(queryString, ApplicationEntity.class);
        query.setParameter("entryNumber", entryNumber);
        query.setParameter("entryDate", entryDate);

        List<ApplicationEntity> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList)) {
            throw new ResourceNotFoundException("Application not found ! Entry number: " + entryNumber + "/" + entryDate);
        }

        return resultList.get(0);
    }

    @Override
    public boolean isFoAppAlreadyAccepted(Integer efilingId) {
        String queryString = "SELECT r FROM ApplicationEntity r WHERE r.efilingId = :efilingId ";
        TypedQuery<ApplicationEntity> query = em.createQuery(queryString, ApplicationEntity.class);
        query.setParameter("efilingId", efilingId);
        List<ApplicationEntity> resultList = query.getResultList();
        return !CollectionUtils.isEmpty(resultList);
    }
}
