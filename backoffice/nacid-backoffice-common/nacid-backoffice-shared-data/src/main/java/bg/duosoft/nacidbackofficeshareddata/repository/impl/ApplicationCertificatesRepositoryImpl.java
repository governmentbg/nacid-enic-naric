package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidbackofficeshareddata.repository.ApplicationCertificatesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ApplicationCertificatesRepositoryImpl extends BaseRepositoryCustomImpl implements ApplicationCertificatesRepository {
    @Override
    public List<ApplicationCertificatesEntity> selectByStatusAndApplicationId(Integer applicationId, String certificateStatus) {
        String queryString = "SELECT a FROM ApplicationCertificatesEntity a where a.application.id = :applicationId and a.certificateStatus = :certificateStatus ";
        TypedQuery<ApplicationCertificatesEntity> query = em.createQuery(queryString, ApplicationCertificatesEntity.class);
        query.setParameter("applicationId", applicationId);
        query.setParameter("certificateStatus", certificateStatus);
        return query.getResultList();
    }

    @Override
    public void saveCertificate(ApplicationCertificatesEntity certificate, ApplicationEntity application) {
        certificate.setApplication(application);
        em.merge(certificate);
    }

    @Override
    public void deleteCertificatesByAttachmentId(Integer attachmentId) {
        String queryString = "DELETE FROM ApplicationCertificatesEntity a where a.applicationAttachedDocId = :attachmentId";
        Query query = em.createQuery(queryString);
        query.setParameter("attachmentId", attachmentId);
        query.executeUpdate();
    }
}
