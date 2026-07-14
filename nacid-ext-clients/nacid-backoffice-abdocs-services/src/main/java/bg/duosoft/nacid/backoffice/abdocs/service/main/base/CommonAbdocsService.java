package bg.duosoft.nacid.backoffice.abdocs.service.main.base;


import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.StoreFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.UploadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.exception.AbdocsException;

import java.time.LocalDateTime;
import java.util.List;

public interface CommonAbdocsService {

    Doc getDocumentById(Integer id);

    Integer getDocumentIdByRegNumber(String regNumber);

    List<DocumentType> getAllDocumentTypes();

    DocumentType getDocumentTypeById(Integer id);

    List<DocumentType> getDocumentTypesByGroupId(Integer groupId);

    Correspondent getCorrespondentById(Integer id);

    Doc createDocument(DocCreation docCreation);

    DownloadFileResponse downloadFile(String fileKey, String fileName, Integer databaseId);

    StoreFileResponse storeFile(byte[] content, String fileName);

    UploadFileResponse uploadFile(Integer docId, byte[] content, String fileName, String description, boolean isPrimary, DocFileVisibility docFileVisibility);

    void cancelDocument(Integer id);

    void unregisterDocument(Integer id);

    void deleteDocument(Integer id);

    void updateRegistrationDate(Integer id, LocalDateTime date);

    void insertDocAction(DocActionRequest docActionRequest);

    Integer selectAbdocsUserIdByUsername(String username);

    void changeParent(Integer docId, Integer parentId);

}
