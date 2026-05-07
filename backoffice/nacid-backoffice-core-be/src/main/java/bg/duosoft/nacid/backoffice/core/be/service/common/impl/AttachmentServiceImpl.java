package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.repository.common.AttachmentRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.AttachmentService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AttachmentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;
    private final AttachmentMapper mapper;

    @Override
    public AttachmentDTO selectById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        AttachmentEntity entity = repository.findById(id).orElse(null);
        return mapper.toDto(entity);
    }

    @LogObjectChange(id = "#result.id", before = "#root.target.selectById(#attachmentDTO.id)", after = "#result", operation = "#attachmentDTO.id == null ? 'create' : 'update'")
    public AttachmentDTO save(AttachmentDTO attachmentDTO) {
        AttachmentEntity entity = mapper.toEntity(attachmentDTO);
        return mapper.toDto(repository.save(entity));
    }

}
