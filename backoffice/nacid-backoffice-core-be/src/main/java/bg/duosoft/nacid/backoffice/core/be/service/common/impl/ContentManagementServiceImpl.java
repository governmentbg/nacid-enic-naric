package bg.duosoft.nacid.backoffice.core.be.service.common.impl;


import bg.duosoft.nacid.backoffice.core.be.repository.common.ContentManagementRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ContentManagementService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ContentManagementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ContentManagementDTO;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ContentManagementMapper;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContentManagementServiceImpl implements ContentManagementService {

    private final ContentManagementRepository contentManagementRepository;
    private final ContentManagementMapper contentManagementMapper;

    @Override
    public String findDataByIdAndActive(String id) {
        String dataByIdAndActive = contentManagementRepository.findDataByIdAndActive(id);
        if (Objects.isNull(dataByIdAndActive)) {
            throw new ResourceNotFoundException();
        }
        return dataByIdAndActive;
    }

    @Override
    public ContentManagementDTO update(String id, String data) {
        ContentManagementEntity contentManagementEntity = contentManagementRepository.findById(id).orElse(null);

        if (Objects.isNull(contentManagementEntity)) {
            throw new ResourceNotFoundException();
        }
        contentManagementEntity.setData(data);
        contentManagementEntity.setDateLastUpdate(LocalDateTime.now());
        contentManagementEntity.setUserLastUpdate(SecurityUtils.getUsername());
        contentManagementRepository.save(contentManagementEntity);
        return contentManagementMapper.toDto(contentManagementEntity);
    }

    @Override
    public List<ContentManagementDTO> findByTypeAndActive(String type) {
        return contentManagementMapper.toDtoList(contentManagementRepository.findByTypeAndActive(type));
    }
}
