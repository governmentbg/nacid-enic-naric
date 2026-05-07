package bg.duosoft.nacid.backoffice.abdocs.client.mock;

import bg.duosoft.nacid.backoffice.abdocs.client.BaseAbdocsClient;
import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.*;
import feign.Request;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseAbdocsClientMock implements BaseAbdocsClient {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public Doc getDocumentById(Integer documentId) {
        Doc doc = new Doc();
        doc.setDocId(documentId);
        doc.setRegUri(generateMockRegNumber(documentId));
        doc.setDocTypeId(1);
        doc.setDocStatus(DocStatus.Processed);
        doc.setRegDate(new Date());
        doc.setDocFiles(new ArrayList<>());
        DocFile df = new DocFile();
        df.setDocId(documentId);
        df.setId(123);
        df.setKey(UUID.randomUUID());
        df.setDbId(1);
        df.setName("mock_file.dat");
        df.setDocFileVisibility(DocFileVisibility.PublicAttachedFile);
        doc.getDocFiles().add(df);
        return doc;
    }

    @Override
    public Integer getDocumentIdByRegNumber(String registerNumber) {
        return registerNumber != null && registerNumber.startsWith("MOCK-") ?
                1 :
                (int) (Math.random() * 1000);
    }

    @Override
    public Correspondent getCorrespondentById(Integer id) {
        Correspondent correspondent = new Correspondent();
        correspondent.setId(id);
        correspondent.setName("Mock Correspondent " + id);
        return correspondent;
    }

    @Override
    public DocCreationResponse createDocument(DocCreation docCreation) {
        DocCreationResponse response = new DocCreationResponse();
        int uniqueId = (UUID.randomUUID().toString().hashCode() & Integer.MAX_VALUE) % 1_000_000;
        response.setCreatedDocumentIds(Collections.singletonList(uniqueId));
        return response;
    }

    @Override
    public Response downloadFile(String key, String fileName, Integer databaseId) {
        DownloadFileResponse mockResponse = new DownloadFileResponse();
        mockResponse.setContent(new byte[0]);
        mockResponse.setFileName(fileName != null ? fileName : "mock_file.dat");
        mockResponse.setType("application/octet-stream");
        Request request = Request.create(
                Request.HttpMethod.GET,
                "/FilesStorage",
                Collections.emptyMap(),
                null,
                Charset.defaultCharset()
        );

        return Response.builder()
                .status(HttpStatus.OK.value())
                .request(request)
                .body(mockResponse.getContent())
                .headers(Collections.singletonMap(
                        "Content-Disposition",
                        Collections.singletonList("attachment; filename=\"" + mockResponse.getFileName() + "\""))
                )
                .build();
    }

    @Override
    public StoreFileResponse storeFile(MultipartFile file) {
        StoreFileResponse response = new StoreFileResponse();
        response.setFileKey(UUID.randomUUID().toString());
        response.setDbId(1);
        response.setFileName(file.getOriginalFilename());
        return response;
    }

    @Override
    public UploadFileResponse uploadFile(DocFile file) {
        UploadFileResponse response = new UploadFileResponse();
        response.setDocId(String.valueOf(file.getDocId()));
        response.setKey(file.getKey() != null ? file.getKey().toString() : UUID.randomUUID().toString());
        response.setName(file.getName());
        response.setDescription(file.getDescription());
        response.setMimeType("application/octet-stream");
        response.setDbId(file.getDbId());
        response.setPrimary(file.isPrimary());
        response.setDocFileVisibility(file.getDocFileVisibility());
        return response;
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        List<DocumentType> types = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DocumentType type = new DocumentType();
            type.setNomValueId(i);
            type.setName("Mock Document Type " + i);
            type.setIsActive(true);
            types.add(type);
        }
        return types;
    }

    @Override
    public List<DocumentType> getDocumentTypesByGroupId(Integer groupId) {
        return getAllDocumentTypes().subList(0, 2);
    }

    @Override
    public DocumentType getDocumentTypeById(Integer id) {
        DocumentType type = new DocumentType();
        type.setNomValueId(id);
        type.setName("Mock Document Type " + id);
        type.setIsActive(true);
        return type;
    }

    @Override
    public Unit selectAbdocsUserIdByUsername(String username) {
        Unit unit = new Unit();
        unit.setId(username != null ? username.hashCode() % 1000 : 1);
        unit.setName(username != null ? "Mock User " + username : "Mock User");
        return unit;
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
        // No-op in mock
    }

    @Override
    public void unregisterDocument(Integer id, String body) {
        // No-op in mock
    }

    @Override
    public void deleteDocument(Integer id) {
        // No-op in mock
    }

    @Override
    public void cancelDocument(Integer documentId, String body) {
        // No-op in mock
    }

    @Override
    public void updateRegistrationDate(Integer documentId, String regDate, String body) {
        // No-op in mock
    }

    @Override
    public void insertDocument(DocActionRequest docActionRequest) {
        // No-op in mock
    }

    private String generateMockRegNumber(int id) {
        String today = LocalDate.now().format(dateFormatter);
        return "MOCK-" + id + "/" + today;
    }
}
