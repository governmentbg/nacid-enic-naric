package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationIdAndStatusDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.AttachedDocRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AttachedDocRepositoryImpl extends BaseRepositoryCustomImpl implements AttachedDocRepository {


    @Override
    @Transactional
    public void updateDocType(Integer attachedDocId, Integer docTypeId) {
        String queryString = "UPDATE ApplicationAttachedDocEntity a " +
                "SET a.documentType.id = :docTypeId " +
                "WHERE a.id = :attachedDocId";

        Query query = em.createQuery(queryString);
        query.setParameter("docTypeId", docTypeId);
        query.setParameter("attachedDocId", attachedDocId);

        query.executeUpdate();
    }


    @Override
    public ApplicationAttachedDocEntity selectById(Integer id) {
        String queryString = "SELECT a FROM ApplicationAttachedDocEntity a where a.id = :id ";
        TypedQuery<ApplicationAttachedDocEntity> query = em.createQuery(queryString, ApplicationAttachedDocEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<ApplicationAttachedDocEntity> selectAllByApplicationId(Integer id) {
        String queryString = "SELECT a FROM ApplicationAttachedDocEntity a where a.application.id = :id ";
        TypedQuery<ApplicationAttachedDocEntity> query = em.createQuery(queryString, ApplicationAttachedDocEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public ApplicationAttachedDocEntity selectByIdAndApplicationId(Integer id, Integer applicationId) {
        String queryString = "SELECT a FROM ApplicationAttachedDocEntity a where a.id = :id and a.application.id = :applicationId ";
        TypedQuery<ApplicationAttachedDocEntity> query = em.createQuery(queryString, ApplicationAttachedDocEntity.class);
        query.setParameter("id", id);
        query.setParameter("applicationId", applicationId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        String attachmentsQueryString = "DELETE FROM ApplicationAttachedDocAttachmentEntity a where a.attachedDoc.id = :id";
        Query attachmentsQuery = em.createQuery(attachmentsQueryString);
        attachmentsQuery.setParameter("id", id);
        attachmentsQuery.executeUpdate();

        String correspondenceDocsQueryString = "DELETE FROM CorrespondenceDocsEntity a where a.applicationAttachedDoc.id = :id";
        Query correspondenceDocsQuery = em.createQuery(correspondenceDocsQueryString);
        correspondenceDocsQuery.setParameter("id", id);
        correspondenceDocsQuery.executeUpdate();


        String queryString = "DELETE FROM ApplicationAttachedDocEntity a where a.id = :id";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public ApplicationIdAndStatusDTO selectApplicationIdAndStatusByAttachmentId(Integer attachmentId) {
        String queryString = "SELECT new bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationIdAndStatusDTO(" +
                "a.application.id, a.application.status.pk.id) " +
                "FROM ApplicationAttachedDocEntity a " +
                "WHERE a.id = :attachmentId";

        TypedQuery<ApplicationIdAndStatusDTO> query = em.createQuery(queryString, ApplicationIdAndStatusDTO.class);
        query.setParameter("attachmentId", attachmentId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
