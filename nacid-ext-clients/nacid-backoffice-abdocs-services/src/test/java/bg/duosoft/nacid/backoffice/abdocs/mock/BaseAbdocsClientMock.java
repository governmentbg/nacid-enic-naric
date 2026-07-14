package bg.duosoft.nacid.backoffice.abdocs.mock;

import bg.duosoft.nacid.backoffice.abdocs.client.BaseAbdocsClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DocCreationResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.SearchCorrespondentResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.StoreFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.UploadFileResponse;
import feign.Request;
import feign.Response;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAbdocsClientMock implements BaseAbdocsClient {
    @Override
    public Doc getDocumentById(Integer documentId) {
        Doc mockDoc = new Doc();
        mockDoc.setDocId(documentId);
        return mockDoc;
    }

    @Override
    public Integer getDocumentIdByRegNumber(String registerNumber) {
        return null;
    }

    @Override
    public Correspondent getCorrespondentById(Integer id) {
        return new Correspondent();
    }

    @Override
    public DocCreationResponse createDocument(DocCreation docCreation) {
        DocCreationResponse mockDoc = new DocCreationResponse();
        mockDoc.setCreatedDocumentIds(Collections.singletonList(1));
        return mockDoc;
    }

    @Override
    public Response downloadFile(String key, String fileName, Integer databaseId) {
        Request mockRequest = Request.create(
                Request.HttpMethod.GET,
                "http://mock.com/api/test",
                Collections.emptyMap(),
                null,
                StandardCharsets.UTF_8,
                null
        );
        Map<String, Collection<String>> headers = new HashMap<>();
        headers.put("Content-Disposition", List.of("attachment; filename=\"test_for_sign.pdf\""));
        headers.put("Content-Type", List.of("application/pdf"));

        return Response.builder()
                       .status(200)
                       .reason("OK")
                       .headers(headers)
                       .body("Success!", StandardCharsets.UTF_8)
                       .request(mockRequest)
                       .build();
    }

    @Override
    public StoreFileResponse storeFile(MultipartFile file) {
        return new StoreFileResponse();
    }

    @Override
    public UploadFileResponse uploadFile(DocFile file) {
        return new UploadFileResponse();
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return Collections.emptyList();
    }

    @Override
    public List<DocumentType> getDocumentTypesByGroupId(Integer groupId) {
        return Collections.emptyList();
    }

    @Override
    public DocumentType getDocumentTypeById(Integer id) {
        return new DocumentType();
    }

    @Override
    public void unregisterDocument(Integer id, String body) {

    }

    @Override
    public void deleteDocument(Integer id) {

    }

    @Override
    public void cancelDocument(Integer documentId, String body) {

    }

    @Override
    public void updateRegistrationDate(Integer documentId, String regDate, String body) {

    }

    @Override
    public void insertDocument(DocActionRequest docActionRequest) {

    }

    @Override
    public Unit selectAbdocsUserIdByUsername(String username) {
        return new Unit();
    }

    @Override
    public void updateDocumentCorrespondents(Integer id, List<DocCorrespondent> correspondents) {

    }

    @Override
    public Correspondent addCorrespondent(Correspondent correspondent) {
        Correspondent res = new Correspondent();
        res.setId(123);
        return res;
    }

    @Override
    public SearchCorrespondentResponse searchCorrespondents(SearchCorrespondentRequest request) {
        return new SearchCorrespondentResponse();
    }

    @Override
    public void changeParent(Integer docId, Integer parentId, String body) {

    }
}
