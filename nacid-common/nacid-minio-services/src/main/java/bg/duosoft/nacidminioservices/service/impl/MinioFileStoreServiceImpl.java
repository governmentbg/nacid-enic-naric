package bg.duosoft.nacidminioservices.service.impl;

import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import bg.duosoft.nacidminioservices.utils.MinioIdGenerator;
import bg.duosoft.nacidminioservices.validation.FileStoreEntryValidator;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioFileStoreServiceImpl implements FileStoreService {
    private final FileStoreEntryValidator fileStoreEntryValidator;
    private final MinioClient minioClient;

    @Override
    public FileStoreEntryBaseDTO saveNewFile(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry) {
        BadRequestValidator.validateRequest(fileStoreEntryValidator, fileStoreEntry, fileGroup, pointer);
        try {
            String generatedFileId;
            do {
                generatedFileId = MinioIdGenerator.generateFileId();
            } while (objectExists(fileStoreEntry.getRootDirectory(), fileStoreEntry.getRelativePath(), generatedFileId));
            FileStoreEntryBaseDTO result = saveFileInStore(fileStoreEntry, generatedFileId);
            return result;
        } catch (Exception e) {
            log.error("Error saving new file in file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileStoreEntryBaseDTO getFileStoreEntryDetailsAndContent(String rootDirectory, String relativePath, String fileId) {
        try {
            String objectStr = buildObjectString(relativePath, fileId);
            FileStoreEntryBaseDTO entry = new FileStoreEntryBaseDTO();
            try (GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder().bucket(rootDirectory).object(objectStr).build())) {
                entry.setContent(response.readAllBytes());
            }
            StatObjectResponse statObject = minioClient.statObject(
                    StatObjectArgs.builder().bucket(rootDirectory).object(objectStr).build()
            );
            fillEntryDetails(statObject, rootDirectory, relativePath, fileId, entry);
            return entry;
        } catch (Exception e) {
            log.error("Error getting file details and content from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileStoreEntryBaseDTO getFileStoreEntryDetailsOnly(String rootDirectory, String relativePath, String fileId) {
        try {
            String objectStr = buildObjectString(relativePath, fileId);
            StatObjectResponse statObject = minioClient.statObject(
                    StatObjectArgs.builder().bucket(rootDirectory).object(objectStr).build()
            );
            FileStoreEntryBaseDTO entry = new FileStoreEntryBaseDTO();
            fillEntryDetails(statObject, rootDirectory, relativePath, fileId, entry);
            return entry;
        } catch (Exception e) {
            log.error("Error getting file details from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileStoreEntryBaseDTO moveFile(String rootDirectoryNew, String relativePathNew, FileStoreEntryBaseDTO entryToMove, Boolean removeOriginal) {
        try {
            FileStoreEntryBaseDTO oldEntry = getFileStoreEntryDetailsAndContent(entryToMove.getRootDirectory(), entryToMove.getRelativePath(), entryToMove.getFileId());
            FileStoreEntryBaseDTO newFileEntry = (FileStoreEntryBaseDTO) oldEntry.clone();
            while (objectExists(rootDirectoryNew, relativePathNew, newFileEntry.getFileId())) {
                String generatedFileId = MinioIdGenerator.generateFileId();
                newFileEntry.setFileId(generatedFileId);
            }
            newFileEntry.setRootDirectory(rootDirectoryNew);
            newFileEntry.setRelativePath(relativePathNew);
            FileStoreEntryBaseDTO result = saveFileInStore(newFileEntry, newFileEntry.getFileId());
            if (removeOriginal != null && removeOriginal) {
                removeFile(oldEntry.getRootDirectory(), oldEntry.getRelativePath(), oldEntry.getFileId());
            }
            return result;
        } catch (Exception e) {
            log.error("Error moving file in file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void copyFile(String rootDirectoryNew, String relativePathNew, String rootDirectoryOld, String relativePathOld, String fileId) {
        String objectOld = buildObjectString(relativePathOld, fileId);
        String objectNew = buildObjectString(relativePathNew, fileId);
        try {
            if (objectExists(rootDirectoryOld, relativePathOld, fileId)) {
                CopyObjectArgs copyObjectArgs = CopyObjectArgs.builder().bucket(rootDirectoryNew).object(objectNew).source(CopySource.builder().bucket(rootDirectoryOld).object(objectOld).build()).build();
                minioClient.copyObject(copyObjectArgs);
            } else {
                throw new ResourceNotFoundException("Copy source not found! Copy source file path: " + rootDirectoryOld + DefaultValue.MINIO_SEPARATOR + relativePathOld + DefaultValue.MINIO_SEPARATOR + fileId);
            }
        } catch (Exception e) {
            log.error("Error with file copy in file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeFile(String rootDirectory, String relativePath, String fileId) {
        try {
            String objectStr = buildObjectString(relativePath, fileId);
            if (objectExists(rootDirectory, relativePath, fileId)) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(rootDirectory).object(objectStr).build()
            );
            }
        } catch (Exception e) {
            log.error("Error removing file from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean fileExists(String rootDirectory, String relativePath, String fileId) {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(rootDirectory).build())) {
                return false;
            } else {
                String objectStr = buildObjectString(relativePath, fileId);
                minioClient.statObject(
                        StatObjectArgs.builder().bucket(rootDirectory).object(objectStr).build()
                );
                return true;
            }
        } catch (ErrorResponseException e) {
            return false;
        } catch (Exception ie) {
            throw new RuntimeException(ie);
        }
    }

    private FileStoreEntryBaseDTO saveFileInStore(FileStoreEntryBaseDTO fileStoreEntry, String fileId) throws Exception {
        String objectStr = buildObjectString(fileStoreEntry.getRelativePath(), fileId);
        Map<String, String> meta = createMetadata(fileStoreEntry, fileId);
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(fileStoreEntry.getRootDirectory())
                .object(objectStr)
                .contentType(fileStoreEntry.getContentType())
                .stream(new ByteArrayInputStream(fileStoreEntry.getContent()), fileStoreEntry.getFileSize(), -1)
                .userMetadata(meta)
                .build();

        minioClient.putObject(putObjectArgs);
        FileStoreEntryBaseDTO result = (FileStoreEntryBaseDTO) fileStoreEntry.clone();
        result.setFileId(fileId);
        result.setContent(null);
        return result;
    }

    private Map<String, String> createMetadata(FileStoreEntryBaseDTO fileStoreEntry, String fileId) {
        Map<String, String> meta = new HashMap<>();
        meta.put(FileConstants.META_FILE_ID, fileId);
        if (fileStoreEntry.getFileSize() != null) {
            meta.put(FileConstants.META_FILE_SIZE, fileStoreEntry.getFileSize().toString());
        } else if (fileStoreEntry.getContent() != null) {
            meta.put(FileConstants.META_FILE_SIZE, fileStoreEntry.getContent().length + "");
        }
        if (fileStoreEntry.getFileName() != null) {
            meta.put(FileConstants.META_FILE_NAME, fileStoreEntry.getFileName());
        }
        if (fileStoreEntry.getAdditionalMetadata() != null) {
            meta.putAll(fileStoreEntry.getAdditionalMetadata());
        }
        return meta;
    }

    private void fillEntryDetails(StatObjectResponse statObject, String rootDirectory, String relativePath, String fileId, FileStoreEntryBaseDTO entry) throws Exception {
        entry.setContentType(statObject.contentType());
        entry.setRootDirectory(rootDirectory);
        entry.setRelativePath(relativePath);
        entry.setFileId(fileId);

        fillEntryDetailsFromMinioMeta(statObject.userMetadata(), entry);
    }

    private void fillEntryDetailsFromMinioMeta(Map<String, String> meta, FileStoreEntryBaseDTO entry) {
        if (meta != null && !meta.isEmpty()) {
            Map<String, String> additionalMeta = new HashMap<>(meta);
            if (meta.containsKey(FileConstants.META_FILE_NAME)) {
                entry.setFileName(meta.get(FileConstants.META_FILE_NAME));
                additionalMeta.remove(FileConstants.META_FILE_NAME);
            }
            if (meta.containsKey(FileConstants.META_FILE_SIZE)) {
                entry.setFileSize(Long.parseLong(meta.get(FileConstants.META_FILE_SIZE)));
                additionalMeta.remove(FileConstants.META_FILE_SIZE);
            }
            if (meta.containsKey(FileConstants.META_FILE_ID)) {
                additionalMeta.remove(FileConstants.META_FILE_ID);
            }
            entry.setAdditionalMetadata(additionalMeta);
        } else {
            entry.setFileName(entry.getFileId());
        }
    }

    private boolean objectExists(String rootDirectory, String relativePath, String fileId) {
        String objectStr = buildObjectString(relativePath, fileId);
        try {
            minioClient.statObject(
                    StatObjectArgs.builder().bucket(rootDirectory).object(objectStr).build()
            );
            return true;
        } catch (ErrorResponseException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildObjectString(String relativePath, String fileId) {
        return relativePath + FileConstants.PATH_SEPARATOR + fileId;
    }

}