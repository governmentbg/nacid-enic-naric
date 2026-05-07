package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationDocflowStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationStatusHistoryEntity;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseStatusRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BaseStatusRepositoryImpl extends BaseRepositoryCustomImpl implements BaseStatusRepository {
    @Override
    public Integer selectLegalFlagByTypeSubtypeStatusCode(String applicationType, String applicationSubtype, String status) {
        String queryString = "SELECT r.legalFlag FROM CfgAppStatusEntity r WHERE r.applicationType.id = :applicationType and (r.applicationSubtype.id is null or r.applicationSubtype.id = :applicationSubtype) and r.status.pk.id = :status";
        TypedQuery<Integer> query = em.createQuery(queryString, Integer.class);
        query.setParameter("applicationType", applicationType);
        query.setParameter("applicationSubtype", applicationSubtype);
        query.setParameter("status", status);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ApplicationStatusHistoryEntity selectLastHistoryStatus(Integer applicationId) {
        String queryString = "SELECT r FROM ApplicationStatusHistoryEntity r WHERE r.dateCreated = (SELECT MAX(x.dateCreated) FROM ApplicationStatusHistoryEntity x WHERE x.applicationId = :applicationId) and r.applicationId = :applicationId order by r.id desc";
        TypedQuery<ApplicationStatusHistoryEntity> query = em.createQuery(queryString, ApplicationStatusHistoryEntity.class);
        query.setParameter("applicationId", applicationId);

        try {
            List<ApplicationStatusHistoryEntity> resultList = query.getResultList();
            return !CollectionUtils.isEmpty(resultList) ? resultList.get(0) : null;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ApplicationStatusHistoryEntity> selectStatusHistoryByApplicationId(Integer applicationId) {
        String queryString = "SELECT r FROM ApplicationStatusHistoryEntity r WHERE r.applicationId = :applicationId order by r.dateCreated desc , r.id desc ";
        TypedQuery<ApplicationStatusHistoryEntity> query = em.createQuery(queryString, ApplicationStatusHistoryEntity.class);
        query.setParameter("applicationId", applicationId);
        return query.getResultList();
    }

    @Override
    public List<ApplicationDocflowStatusHistoryEntity> selectDocflowStatusHistoryByApplicationId(Integer applicationId) {
        String queryString = "SELECT r FROM ApplicationDocflowStatusHistoryEntity r WHERE r.applicationId = :applicationId order by r.dateCreated desc , r.id desc ";
        TypedQuery<ApplicationDocflowStatusHistoryEntity> query = em.createQuery(queryString, ApplicationDocflowStatusHistoryEntity.class);
        query.setParameter("applicationId", applicationId);
        return query.getResultList();
    }
}
