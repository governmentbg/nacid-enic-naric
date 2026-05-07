package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.VAttachmentsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentsViewDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.VAttachmentsMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseAttachmentsViewRepository;
import bg.duosoft.nacidbackofficeshareddata.service.BaseAttachmentsViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BaseAttachmentsViewServiceImpl implements BaseAttachmentsViewService {

    private final BaseAttachmentsViewRepository repository;
    private final VAttachmentsMapper mapper;

    @Override
    public AttachmentsViewDTO selectByAttachmentId(Integer attachmentId) {
        if (Objects.isNull(attachmentId)) {
            return null;
        }

        VAttachmentsEntity entity = repository.selectByAttachmentId(attachmentId);
        return mapper.toDto(entity);
    }

    @Override
    public List<AttachmentsViewDTO> selectTransferredAttachmentsByApplicationId(Integer applicationId) {
        if (Objects.isNull(applicationId)) {
            return null;
        }

        List<VAttachmentsEntity> entities = repository.selectTransferredAttachmentsByApplicationId(applicationId);
        return mapper.toDtoList(entities);
    }
}
