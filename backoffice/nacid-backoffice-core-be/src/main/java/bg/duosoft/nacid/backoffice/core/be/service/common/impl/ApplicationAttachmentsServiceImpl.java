package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.repository.common.ApplicationAttachmentsRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationAttachmentsService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToDocCategoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacidbackofficeshareddata.service.impl.BaseApplicationAttachmentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationAttachmentsServiceImpl extends BaseApplicationAttachmentServiceImpl implements ApplicationAttachmentsService {
    private final ApplicationAttachmentsRepository applicationAttachmentsRepository;
    private final ApplicationAttachedDocMapper applicationAttachedDocMapper;

    @Override
    public List<AttachedDocDTO> selectApplicationAttachments(Integer applicationId, String direction, Boolean finalized) {
        List<ApplicationAttachedDocEntity> applicationAttachedDocEntities = applicationAttachmentsRepository.selectByApplicationIdAndDirection(applicationId, direction, DocCategory.APP_ATTACHMENTS.code());
        List<ApplicationAttachedDocEntity> resultList = getWithFinalizedCondition(applicationAttachedDocEntities, finalized);
        List<AttachedDocDTO> attachedDocs = applicationAttachedDocMapper.toDtoList(resultList);
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            attachedDocs.sort(Comparator.comparing(AttachedDocDTO::getId).reversed());
        }
        return attachedDocs;
    }

    @Override
    public List<AttachedDocDTO> selectAttachmentsByDocCategory(Integer applicationId, String direction, String docCategory) {
        List<ApplicationAttachedDocEntity> applicationAttachedDocEntities = applicationAttachmentsRepository.selectByApplicationIdAndDirection(applicationId, direction, docCategory);
        List<AttachedDocDTO> attachedDocs = applicationAttachedDocMapper.toDtoList(applicationAttachedDocEntities);
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            attachedDocs.sort(Comparator.comparing(AttachedDocDTO::getId).reversed());
        }
        return attachedDocs;
    }

    private List<ApplicationAttachedDocEntity> getWithFinalizedCondition(List<ApplicationAttachedDocEntity> applicationAttachedDocEntities, Boolean finalized) {
        List<ApplicationAttachedDocEntity> finalizedDocs = new ArrayList<>();
        if (Objects.nonNull(finalized)) {
            if (!CollectionUtils.isEmpty(applicationAttachedDocEntities)) {
                for (ApplicationAttachedDocEntity applicationAttachedDocEntity : applicationAttachedDocEntities) {

                    List<CfgDocTypeToDocCategoryEntity> details = applicationAttachedDocEntity.getDocumentType().getDetails();
                    if (!finalized && CollectionUtils.isEmpty(details)) {
                        finalizedDocs.add(applicationAttachedDocEntity);
                        continue;
                    }
                    if (!CollectionUtils.isEmpty(details)) {
                        CfgDocTypeToDocCategoryEntity cfgDocTypeToDocCategoryEntity = details.stream().filter(r -> Objects.nonNull(r.getFinalizationType())).findFirst().orElse(null);
                        if ((finalized && Objects.nonNull(cfgDocTypeToDocCategoryEntity)) || (!finalized && Objects.isNull(cfgDocTypeToDocCategoryEntity))) {
                            finalizedDocs.add(applicationAttachedDocEntity);
                        }
                    }

                }
            }
        } else {
            return applicationAttachedDocEntities;
        }

        return finalizedDocs;
    }

    @Override
    public AttachedDocDTO selectById(Integer id) {
        return applicationAttachedDocMapper.toDto(applicationAttachmentsRepository.findById(id).orElse(null));
    }

    @Override
    @LogObjectChange(id = "#result.id", before = "#root.target.selectById(#attachedDocId)", after = "#result", operation = "'update'", service = "AppAttachmentDocflowIdUpdate")
    public AttachedDocDTO updateDocflowId(Integer attachedDocId, String docflowId) {
        ApplicationAttachedDocEntity entity = applicationAttachmentsRepository.findById(attachedDocId).orElse(null);
        if (Objects.isNull(entity)) {
            throw new RuntimeException("Cannot find AttachedDocEntity with ID = " + attachedDocId);
        }

        entity.setDocflowId(docflowId);
        return applicationAttachedDocMapper.toDto(applicationAttachmentsRepository.save(entity));
    }

}
