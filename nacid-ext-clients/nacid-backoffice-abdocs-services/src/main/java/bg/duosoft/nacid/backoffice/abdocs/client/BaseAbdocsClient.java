package bg.duosoft.nacid.backoffice.abdocs.client;

import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DocCreationResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.SearchCorrespondentResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.StoreFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.UploadFileResponse;
import feign.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BaseAbdocsClient {

    @GetMapping(value = "/docs/{docId}")
    Doc getDocumentById(@PathVariable("docId") Integer documentId);

    @GetMapping(value = "/docs/byRegistrationNumber")
    Integer getDocumentIdByRegNumber(@RequestParam("number") String registerNumber);

    @GetMapping(value = "/corrs/{id}")
    Correspondent getCorrespondentById(@PathVariable("id") Integer id);

    @PostMapping(value = "/docs")
    DocCreationResponse createDocument(@RequestBody DocCreation docCreation);

    @GetMapping(value = "/FilesStorage")
    Response downloadFile(@RequestParam("fileKey") String key, @RequestParam("fileName") String fileName, @RequestParam("dbId") Integer databaseId);

    @PostMapping(value = "/FilesStorage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    StoreFileResponse storeFile(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/docFile")
    UploadFileResponse uploadFile(@RequestBody DocFile file);

    @GetMapping(value = "/nomenclatures/docType")
    List<DocumentType> getAllDocumentTypes();

    @GetMapping(value = "/nomenclatures/docType")
    List<DocumentType> getDocumentTypesByGroupId(@RequestParam("parentValueId") Integer groupId);

    @GetMapping(value = "/nomenclatures/docType/{id}")
    DocumentType getDocumentTypeById(@PathVariable("id") Integer id);

    @PostMapping(value = "/DocRegistration/{docId}/unregister", consumes = "application/json")
    void unregisterDocument(@PathVariable("docId") Integer id, @RequestBody String body);

    @DeleteMapping(value = "/docs/{docId}")
    void deleteDocument(@PathVariable("docId") Integer id);

    @PostMapping(value = "/docs/{docId}/cancel", consumes = "application/json")
    void cancelDocument(@PathVariable("docId") Integer documentId, @RequestBody String body);

    @PostMapping(value = "/DocRegistration/{id}/changeRegDate?force=true", consumes = "application/json")
    void updateRegistrationDate(@PathVariable("id") Integer documentId, @RequestParam("regDate") String regDate, @RequestBody String body);

    @PostMapping(value = "/DocActionRequests")
    void insertDocument(@RequestBody DocActionRequest docActionRequest);

    @GetMapping(value = "/units/{username}")
    Unit selectAbdocsUserIdByUsername(@PathVariable("username") String username);

    @PutMapping(value = "/docs/{docId}/updateCorrespondents", consumes = "application/json")
    void updateDocumentCorrespondents(@PathVariable("docId") Integer id, @RequestBody List<DocCorrespondent> correspondents);

    @PostMapping("/corrs")
    Correspondent addCorrespondent(@RequestBody Correspondent correspondent);

    @PostMapping("/corrs/getFiltered")
    SearchCorrespondentResponse searchCorrespondents(@RequestBody SearchCorrespondentRequest request);

    @PostMapping(value = "/docs/{docId}/changeDocParent?newDocId={parentId}", consumes = "application/json")
    void changeParent(@PathVariable("docId") Integer docId, @PathVariable("parentId") Integer parentId, @RequestBody String body);


}
