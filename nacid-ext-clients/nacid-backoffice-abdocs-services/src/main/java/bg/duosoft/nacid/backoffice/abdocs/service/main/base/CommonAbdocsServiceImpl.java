
package bg.duosoft.nacid.backoffice.abdocs.service.main.base;

import bg.duosoft.nacid.backoffice.abdocs.client.AbdocsAdminClient;
import bg.duosoft.nacid.backoffice.abdocs.client.BaseAbdocsClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DocCreationResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.StoreFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.UploadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.exception.AbdocsException;
import bg.duosoft.nacid.backoffice.abdocs.util.ContentDisposition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public abstract class CommonAbdocsServiceImpl implements CommonAbdocsService {

    private static final String DEFAULT_ATTACHMENT_FILE_NAME = "noname";

    @Autowired
    private AbdocsAdminClient abdocsAdminClient;

    public abstract BaseAbdocsClient getClient();

    @Override
    public Doc getDocumentById(Integer id) {
        return getClient().getDocumentById(id);
    }

    @Override
    public Integer getDocumentIdByRegNumber(String regNumber) {
        return getClient().getDocumentIdByRegNumber(regNumber);
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return getClient().getAllDocumentTypes();
    }

    @Override
    public DocumentType getDocumentTypeById(Integer id) {
        return getClient().getDocumentTypeById(id);
    }

    @Override
    public List<DocumentType> getDocumentTypesByGroupId(Integer groupId) {
        return getClient().getDocumentTypesByGroupId(groupId);
    }

    @Override
    public Correspondent getCorrespondentById(Integer id) {
        return getClient().getCorrespondentById(id);
    }

    @Override
    public Doc createDocument(DocCreation docCreation) {
        log.trace("[ABDOCS] Create document json: \n" + createJson(docCreation));
        DocCreationResponse document = getClient().createDocument(docCreation);
        if (Objects.isNull(document)) {
            throw new RuntimeException("Created document response is empty !");
        }

        List<Integer> createdDocumentIds = document.getCreatedDocumentIds();
        if (createdDocumentIds.size() != 1) {
            throw new RuntimeException("There are more than one register document ids !");
        }

        Integer docId = createdDocumentIds.get(0);
        try {
            return getDocumentById(docId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            try {
                deleteDocument(docId);
            } catch (Exception ex) {
                log.error("[ABDOCS] Cannot delete document with id " + docId);
                log.error(ex.getMessage(), ex);
            }
            throw e;
        }
    }

    @Override
    public DownloadFileResponse downloadFile(String fileKey, String fileName, Integer databaseId) {
        Response response = getClient().downloadFile(fileKey, fileName, databaseId);
        if (HttpStatus.OK.value() != response.status()) {
            throw new RuntimeException("File cannot be downloaded! Key: " + fileKey + ", Name: " + fileKey + ", Database: " + databaseId);
        }

        DownloadFileResponse result = new DownloadFileResponse();
        try {
            result.setContent(response.body().asInputStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collection<String> contentDisposition = response.headers().get(HttpHeaders.CONTENT_DISPOSITION);
        if (!CollectionUtils.isEmpty(contentDisposition)) {
            result.setFileName(ContentDisposition.parse(contentDisposition.iterator().next()).getFilename());
        }

        Collection<String> contentType = response.headers().get(HttpHeaders.CONTENT_TYPE);
        if (!CollectionUtils.isEmpty(contentType)) {
            result.setType(contentType.iterator().next());
        }

        return result;
    }

    @Override
    public void cancelDocument(Integer id) {
        abdocsAdminClient.cancelDocument(id, "");
    }

    @Override
    public void unregisterDocument(Integer id) {
        abdocsAdminClient.unregisterDocument(id, "");
    }

    @Override
    public void deleteDocument(Integer id) {
        try {
            unregisterDocument(id);
        } catch (Exception e) {
            log.debug("[ABDOCS]Cannot unregister document with ID " + id, e);
        }
        abdocsAdminClient.deleteDocument(id);
    }

    @Override
    public StoreFileResponse storeFile(byte[] content, String fileName) {
        try {
            log.debug("[ABDOCS] Uploading file to file storage... {}", fileName);
            MultipartFile multipartFile = new AbdocsMultipartFile(content, fileName);
            StoreFileResponse response = getClient().storeFile(multipartFile);
            response.setFileName(fileName);
            log.debug("[ABDOCS] File is uploaded successfully ! {}", fileName);
            return response;
        } catch (Exception e) {
            log.debug("[ABDOCS] Error occurred during file upload! {}", fileName);
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public UploadFileResponse uploadFile(Integer docId, byte[] content, String fileName, String description, boolean isPrimary, DocFileVisibility docFileVisibility) {
        try {
            log.debug("[ABDOCS] Adding file to document... {} , {}", fileName, docId);
            if (!StringUtils.hasText(fileName)) {
                fileName = DEFAULT_ATTACHMENT_FILE_NAME;
            }

            StoreFileResponse storageResponse = storeFile(content, fileName);

            DocFile docFile = new DocFile();
            docFile.setDbId(storageResponse.getDbId());
            docFile.setKey(UUID.fromString(storageResponse.getFileKey()));
            docFile.setName(fileName);
            docFile.setDescription(description);
            docFile.setDocFileVisibility(docFileVisibility);
            docFile.setPrimary(isPrimary);
            docFile.setDocId(docId);

            UploadFileResponse response = getClient().uploadFile(docFile);
            log.debug("[ABDOCS] File is added successfully to document ! {}, {}", fileName, docId);
            return response;
        } catch (Exception e) {
            log.debug("[ABDOCS] Error occurred during adding file to document! {}", fileName);
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateRegistrationDate(Integer id, LocalDateTime date) {
        if (Objects.isNull(date)) {
            throw new RuntimeException("[ABDOCS] Cannot change date of document with ID = " + id + " because date is empty !");
        }
        try {
            log.debug("[ABDOCS] Update registration date for document {}", id);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String dateString = date.format(formatter);
            getClient().updateRegistrationDate(id, dateString, "");
            log.debug("[ABDOCS] Registration number has been updated successfully! Document ID: {}, Date: {}", id, dateString);
        } catch (Exception e) {
            log.debug("[ABDOCS] Error occurred during registration number update! Document ID: {}", id);
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public void insertDocAction(DocActionRequest docActionRequest) {
        if (Objects.isNull(docActionRequest)) {
            throw new AbdocsException("[ABDOCS] Cannot insert action because docActionRequest object is empty !");
        }

        Integer docId = docActionRequest.getDocId();
        if (Objects.isNull(docId)) {
            throw new AbdocsException("[ABDOCS] Cannot insert action because docId is empty !");
        }

        try {
            log.debug("[ABDOCS] Insert action for document {}", docId);
            getClient().insertDocument(docActionRequest);
            log.debug("[ABDOCS] Action has been inserted successfully! Document ID: {}", docId);
        } catch (Exception e) {
            log.debug("[ABDOCS] Error occurred during action insertion! Document ID: {}", docId);
            log.error(e.getMessage(), e);
            throw new AbdocsException(e.getMessage(), e);
        }
    }

    @Override
    public Integer selectAbdocsUserIdByUsername(String username) {
        log.debug("[ABDOCS] Selecting abdocs id for username {} ... ", username);
        if (!StringUtils.hasText(username)) {
            return null;
        }

        Unit unit = getClient().selectAbdocsUserIdByUsername(username);
        if (Objects.isNull(unit)) {
            return null;
        }

        return unit.getId();
    }

    @Override
    public void changeParent(Integer docId, Integer parentId) {
        log.debug("[ABDOCS] Change doc {} to parent {}... ", docId, parentId);
        if (Objects.isNull(parentId) || Objects.isNull(docId)) {
            return;
        }

        getClient().changeParent(docId, parentId, "");
    }

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }
    public static <T> String createJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
