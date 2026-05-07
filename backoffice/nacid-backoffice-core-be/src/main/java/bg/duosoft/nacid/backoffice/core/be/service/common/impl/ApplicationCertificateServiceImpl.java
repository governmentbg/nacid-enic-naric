package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFileVisibility;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocStatus;
import bg.duosoft.nacid.backoffice.core.be.repository.common.ApplicationCertificateRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsCoreService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationAttachedDocService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationCertificateService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationCertificatesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationCertificateServiceImpl implements ApplicationCertificateService {

    private final ApplicationCertificateRepository applicationCertificateRepository;
    private final ApplicationsService applicationsService;
    private final AbdocsCoreService abdocsCoreService;
    private final ApplicationAttachedDocService applicationAttachedDocService;
    private final ApplicationCertificatesMapper mapper;

    @Override
    public List<ApplicationCertificatesDTO> selectCertificatesForFO(Integer efilingId) {
        Integer apnId = applicationsService.selectApplicationIdByEfilingId(efilingId);
        if (Objects.isNull(apnId)) {
            return null;
        }

        List<ApplicationCertificatesEntity> applicationCertificatesEntities = applicationCertificateRepository.selectPublishedCertificatesByApnId(apnId);
        if (CollectionUtils.isEmpty(applicationCertificatesEntities)) {
            return null;
        }

        ArrayList<ApplicationCertificatesDTO> certificates = new ArrayList<>();
        for (ApplicationCertificatesEntity certificate : applicationCertificatesEntities) {
            String docflowId = applicationAttachedDocService.selectDocflowIdById(certificate.getApplicationAttachedDocId());
            if (StringUtils.hasText(docflowId)) {
                Doc doc = abdocsCoreService.selectAbdocsDoc(Integer.valueOf(docflowId));
                if (Objects.nonNull(doc) && doc.getDocStatus().equals(DocStatus.Finished)
                        && Objects.nonNull(doc.getRegDate()) && StringUtils.hasText(doc.getRegUri())
                        && doc.getDocFiles().stream().anyMatch(x -> x.getDocFileVisibility().equals(DocFileVisibility.PublicAttachedFile))) {
                    certificates.add(mapper.toDto(certificate));
                }
            }
        }

        return certificates;
    }
}
