package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationIdAndStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.AttachedDocRepository;
import bg.duosoft.nacidbackofficeshareddata.service.AttachedDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttachedDocServiceImpl implements AttachedDocService {
    @Autowired
    protected AttachedDocRepository applicationAttachmentRepository;
    @Autowired
    protected ApplicationAttachedDocMapper applicationAttachedDocMapper;


    @Override
    public void updateDocType(Integer attachedDocId, Integer docTypeId) {
        applicationAttachmentRepository.updateDocType(attachedDocId, docTypeId);
    }

    @Override
    public AttachedDocDTO selectById(Integer id) {
        return applicationAttachedDocMapper.toDto(applicationAttachmentRepository.selectById(id));
    }

    @Override
    public List<AttachedDocDTO> selectAllByApplicationId(Integer id) {
        return applicationAttachedDocMapper.toDtoList(applicationAttachmentRepository.selectAllByApplicationId(id));
    }

    @Override
    public AttachedDocDTO selectByIdAndApplicationId(Integer id, Integer applicationId) {
        return applicationAttachedDocMapper.toDto(applicationAttachmentRepository.selectByIdAndApplicationId(id, applicationId));
    }

    @Override
    public void delete(Integer id) {
        applicationAttachmentRepository.delete(id);
    }

    @Override
    public ApplicationIdAndStatusDTO selectApplicationIdAndStatusByAttachmentId(Integer attachmentId) {
        return applicationAttachmentRepository.selectApplicationIdAndStatusByAttachmentId(attachmentId);
    }
}
