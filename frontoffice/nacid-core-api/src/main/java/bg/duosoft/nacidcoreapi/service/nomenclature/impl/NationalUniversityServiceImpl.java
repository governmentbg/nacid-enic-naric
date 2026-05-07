package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.NationalUniversityRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.NationalUniversityService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.NationalUniversityValidator;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.NationalUniversityEntity;
import bg.duosoft.nacidcoredata.mapper.nomenclature.NationalUniversityMapper;
import bg.duosoft.nacidcoredata.util.FileUtils;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidminiodto.util.FileDirectories;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NationalUniversityServiceImpl extends NomenclatureServiceBaseImpl<String, NationalUniversityDTO, NationalUniversityDataFilterDTO> implements NationalUniversityService {
    private final NationalUniversityRepository repository;
    private final NationalUniversityMapper mapper;
    private final NationalUniversityValidator validator;
    private final FileStoreService fileStoreService;

    @Override
    protected NationalUniversityRepository getRepository() {
        return repository;
    }


    @Override
    protected NationalUniversityMapper getMapper() {
        return mapper;
    }

    @Override
    public NationalUniversityValidator getValidator() {
        return validator;
    }


    @Override
    protected void beforeDelete(String id) {
        deleteOldLogo(id);
    }

    @Override
    protected void beforeSave(NationalUniversityDTO dto) {
        if (StringUtils.hasText(dto.getLogoRelativePath())) {
            moveLogo(dto, false);
        }
    }

    @Override
    protected void beforeUpdate(NationalUniversityDTO dto) {
        if (StringUtils.hasText(dto.getLogoRelativePath()) && !dto.getLogoRelativePath().contains(FileDirectories.NATIONAL_UNIVERSITY)) {
            moveLogo(dto, true);
        }
    }

    @Transactional
    public void updateAllToInactive() {
        repository.updateAllToInactive();
    }

    private void moveLogo(NationalUniversityDTO dto, boolean isUpdate) {
        Pair<String, String> stringStringPair = FileUtils.separateFilePath(dto.getLogoRelativePath());
        FileStoreEntryBaseDTO tempFile = fileStoreService.getFileStoreEntryDetailsAndContent(FileConstants.TEMP_ROOT_DIRECTORY, FileConstants.PORTAL_ROOT_DIRECTORY, stringStringPair.getSecond());
        FileStoreEntryBaseDTO savedDTO;
        savedDTO = fileStoreService.moveFile(FileConstants.PORTAL_ROOT_DIRECTORY, FileDirectories.NATIONAL_UNIVERSITY, tempFile, true);
        if (isUpdate) {
            deleteOldLogo(dto.getId());
        }
        dto.setLogoRelativePath("/" + FileDirectories.NATIONAL_UNIVERSITY + "/" + savedDTO.getFileId());
    }

    private void deleteOldLogo(String id) {
        String oldLogoRelativePath = getLogoRelativePath(id);
        if (StringUtils.hasText(oldLogoRelativePath)) {
            fileStoreService.removeFile(FileConstants.PORTAL_ROOT_DIRECTORY, FileDirectories.NATIONAL_UNIVERSITY, oldLogoRelativePath);
        }
    }

    private String getLogoRelativePath(String id) {
        NationalUniversityEntity nationalUniversityEntity = repository.findById(id).orElse(null);

        if (Objects.isNull(nationalUniversityEntity)) {
            throw new ResourceNotFoundException();
        }

        if (!StringUtils.hasText(nationalUniversityEntity.getLogoRelativePath()))
            return null;

        return FileUtils.separateFilePath(nationalUniversityEntity.getLogoRelativePath()).getSecond();
    }
}
