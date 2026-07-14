package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.VAttachmentsEntity;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseAttachmentsViewRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BaseAttachmentsViewRepositoryImpl extends BaseRepositoryCustomImpl implements BaseAttachmentsViewRepository {

    @Override
    public VAttachmentsEntity selectByAttachmentId(Integer attachmentId) {
        String queryString = "SELECT r FROM VAttachmentsEntity r WHERE r.attachmentId = :attachmentId ";
        TypedQuery<VAttachmentsEntity> query = em.createQuery(queryString, VAttachmentsEntity.class);
        query.setParameter("attachmentId", attachmentId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<VAttachmentsEntity> selectTransferredAttachmentsByApplicationId(Integer applicationId) {
        String queryString = "SELECT r FROM VAttachmentsEntity r WHERE r.applicationId = :applicationId and r.docflowKey is not null";
        TypedQuery<VAttachmentsEntity> query = em.createQuery(queryString, VAttachmentsEntity.class);
        query.setParameter("applicationId", applicationId);

        List<VAttachmentsEntity> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }

        return resultList;
    }
}
