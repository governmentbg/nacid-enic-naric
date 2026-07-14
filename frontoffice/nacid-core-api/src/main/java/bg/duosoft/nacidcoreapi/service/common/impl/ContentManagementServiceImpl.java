package bg.duosoft.nacidcoreapi.service.common.impl;

import bg.duosoft.nacidcoreapi.repository.common.ContentManagementRepository;
import bg.duosoft.nacidcoreapi.service.common.ContentManagementService;
import bg.duosoft.nacidcoredata.domain.entity.ContentManagementEntity;
import bg.duosoft.nacidfrontofficedto.contentmgmt.ContentManagementDTO;
import bg.duosoft.nacidshared.web.service.CacheService;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidcoredata.mapper.ContentManagementMapper;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContentManagementServiceImpl implements ContentManagementService {

    private final ContentManagementRepository contentManagementRepository;
    private final ContentManagementMapper contentManagementMapper;
    private final CacheService cacheService;
    private final String CACHE_NAME="ContentManagementServiceImpl";

    @Override
    @Cacheable(value = CACHE_NAME, key = "'content-management-data-' + #id")
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
        contentManagementEntity.setDateLastUpdate(DateUtils.convertToDate(LocalDate.now()));
        contentManagementEntity.setUserLastUpdate(SecurityUtils.getUsername());
        contentManagementRepository.save(contentManagementEntity);
        cacheService.clearCache(CACHE_NAME);
        return contentManagementMapper.toDto(contentManagementEntity);
    }

    @Override
    public List<ContentManagementDTO> findByTypeAndActive(String type) {
        return contentManagementMapper.toDtoList( contentManagementRepository.findByTypeAndActive(type));
    }
}
