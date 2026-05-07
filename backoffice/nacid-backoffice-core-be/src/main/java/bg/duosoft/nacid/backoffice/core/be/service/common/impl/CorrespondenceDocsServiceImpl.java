package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFileVisibility;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocStatus;
import bg.duosoft.nacid.backoffice.core.be.repository.common.CorrespondenceDocsRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsCoreService;
import bg.duosoft.nacid.backoffice.core.be.service.common.CorrespondenceDocsService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.CorrespondenceDocsEntity;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidservicesclient.client.ServicesApplicationCorrespondenceAdminClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CorrespondenceDocsServiceImpl implements CorrespondenceDocsService {

    private final CorrespondenceDocsRepository repository;
    private final AbdocsCoreService abdocsCoreService;
    private final ServicesApplicationCorrespondenceAdminClient servicesApplicationCorrespondenceAdminClient;

    @Override
    public void processCorrespondenceDocs() {
        List<CorrespondenceDocsEntity> correspondenceDocsEntities = repository.selectValidCorrespondenceDocs(LocalDateTime.now().minusMonths(1));
        for (CorrespondenceDocsEntity c : correspondenceDocsEntities) {
            Doc doc = abdocsCoreService.selectAbdocsDoc(Integer.valueOf(c.getApplicationAttachedDoc().getDocflowId()));
            if (Objects.nonNull(doc) && doc.getDocStatus().equals(DocStatus.Finished)
                    && Objects.nonNull(doc.getRegDate()) && StringUtils.hasText(doc.getRegUri())
                    && doc.getDocFiles().stream().anyMatch(x -> x.getDocFileVisibility().equals(DocFileVisibility.PublicAttachedFile))) {
                c.setRegistrationNumber(doc.getRegUri().split("/")[0]);
                c.setRegistrationDate(doc.getRegDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                servicesApplicationCorrespondenceAdminClient.createApplicationCorrespondence(fillCorrespondenceDto(c));
                c.setFoSendDate(LocalDateTime.now());
                repository.save(c);
            }
        }
    }

    private ApplicationCorrespondenceDTO fillCorrespondenceDto(CorrespondenceDocsEntity entity) {
        ApplicationCorrespondenceDTO applicationCorrespondenceDTO = new ApplicationCorrespondenceDTO();
        applicationCorrespondenceDTO.setApplicationId(entity.getApplicationAttachedDoc().getApplication().getEfilingId());
        applicationCorrespondenceDTO.setRegistrationDate(entity.getRegistrationDate());
        applicationCorrespondenceDTO.setRegistrationNumber(entity.getRegistrationNumber().split("/")[0]);
        applicationCorrespondenceDTO.setRefId(entity.getApplicationAttachedDoc().getId());
        applicationCorrespondenceDTO.setAbout(entity.getApplicationAttachedDoc().getDocumentType().getName());
        return applicationCorrespondenceDTO;
    }
}
