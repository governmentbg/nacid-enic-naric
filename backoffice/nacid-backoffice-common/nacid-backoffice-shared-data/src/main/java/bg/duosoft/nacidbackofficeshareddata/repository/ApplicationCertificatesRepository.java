package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;


import java.util.List;

public interface ApplicationCertificatesRepository {
    List<ApplicationCertificatesEntity> selectByStatusAndApplicationId(Integer applicationId, String certificateStatus);

    void saveCertificate(ApplicationCertificatesEntity certificate, ApplicationEntity application);

    void deleteCertificatesByAttachmentId(Integer attachmentId);
}
