package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationCertificatesMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.ApplicationCertificatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationCertificatesService {
    private final ApplicationCertificatesRepository applicationCertificatesRepository;
    private final ApplicationCertificatesMapper applicationCertificatesMapper;
    private final ApplicationMapper applicationMapper;

    public List<ApplicationCertificatesDTO> selectByStatusAndApplicationId(Integer applicationId, String certificateStatus) {
        List<ApplicationCertificatesEntity> applicationCertificatesEntities = applicationCertificatesRepository.selectByStatusAndApplicationId(applicationId, certificateStatus);
        return applicationCertificatesMapper.toDtoList(applicationCertificatesEntities);
    }

    public void saveCertificate(ApplicationCertificatesDTO certificate, ApplicationDTO application) {
        applicationCertificatesRepository.saveCertificate(applicationCertificatesMapper.toEntity(certificate), applicationMapper.toEntity(application));
    }
    public void deleteCertificatesByAttachmentId(Integer attachmentId) {
        applicationCertificatesRepository.deleteCertificatesByAttachmentId(attachmentId);
    }
}
