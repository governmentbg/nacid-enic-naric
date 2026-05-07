package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ErrorLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseErrorLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Slf4j
@Repository
public class BaseErrorLogRepositoryImpl extends BaseRepositoryCustomImpl implements BaseErrorLogRepository {

    @Override
    public ErrorLogEntity insertRecord(ErrorLogEntity entity) {
        if (Objects.isNull(entity)) {
            throw new RuntimeException("[ERROR LOG] Cannot insert error log, because entity object is empty !");
        }

        LocalDateTime createdDate = entity.getCreatedDate();
        if (Objects.isNull(createdDate)) {
            entity.setCreatedDate(LocalDateTime.now());
        }

        em.persist(entity);
        return entity;
    }

    @Override
    public ErrorLogEntity resolveRecord(ErrorLogEntity entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            throw new RuntimeException("[ERROR LOG] Cannot resolve error log, because entity object is empty !");
        }

        LocalDateTime resolvedDate = entity.getResolvedDate();
        if (Objects.isNull(resolvedDate)) {
            entity.setResolvedDate(LocalDateTime.now());
        }

        em.merge(entity);
        return entity;
    }

    @Override
    public ErrorLogEntity selectById(Integer id) {
        String queryString = "SELECT r FROM ErrorLogEntity r WHERE r.id = :id";
        TypedQuery<ErrorLogEntity> query = em.createQuery(queryString, ErrorLogEntity.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ErrorLogEntity selectByReferenceIdAndType(ErrorLogType type, String referenceId) {
        String queryString = "SELECT r FROM ErrorLogEntity r WHERE r.referenceId = :referenceId and r.errorType = :errorType";
        TypedQuery<ErrorLogEntity> query = em.createQuery(queryString, ErrorLogEntity.class);
        query.setParameter("referenceId", referenceId);
        query.setParameter("errorType", type.code());

        List<ErrorLogEntity> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }

        if (resultList.size() > 1) {
            throw new RuntimeException("There are more than 1 record with reference id = " + referenceId + " and type = " + type.code());
        }

        return resultList.get(0);
    }
}
