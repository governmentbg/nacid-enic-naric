package bg.duosoft.nacid.backoffice.abdocs;

import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.UploadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("bg.duosoft.nacid.backoffice.abdocs")
class NacidBackofficeAbdocsServicesApplicationTests {

    @Autowired
    private AbdocsAdminService abdocsAdminService;

    @Test
    void getDocumentById() {
        Integer docId = 313659;
        Doc doc = abdocsAdminService.getDocumentById(docId);
        Assert.assertNotNull(doc);
        Assert.assertEquals(doc.getDocId(), docId);
    }


    @Test
    public void downloadFile() {
        DownloadFileResponse o = abdocsAdminService.downloadFile("3e27f990-3f1c-48d7-a4b2-9b4ac0d47b4f", "test_for_sign.pdf", 1);
        Assert.assertNotNull(o);
        Assert.assertEquals(o.getFileName(), "test_for_sign.pdf");
        Assert.assertEquals(o.getType(), "application/pdf");
        Assert.assertNotNull(o.getContent());
    }

    @Test
    @Disabled
    public void uploadFile() throws IOException {
        FileSystemResource file = new FileSystemResource("/Users/denislavveizov/Downloads/receipt-13.pdf");
        byte[] bytes = file.getInputStream().readAllBytes();
        UploadFileResponse response = abdocsAdminService.uploadFile(313986, bytes, "test.pdf", "Denislav", false, DocFileVisibility.PrivateAttachedFile);
        DownloadFileResponse downloadFileResponse = abdocsAdminService.downloadFile(response.getKey(), response.getName(), response.getDbId());
        System.out.println(1);
    }

    @Test
    public void createDoc() {
        DocCreation docCreation = generateDocCreationObject();
        Doc document = abdocsAdminService.createDocument(generateDocCreationObject());
        Doc outputDoc = abdocsAdminService.createDocument(generateOutputDocument(document));

        Assert.assertNotNull(document);
    }


    @Test
    public void createChildDoc() {

        Doc documentById = abdocsAdminService.getDocumentById(313997);

        DocCreation docCreation = new DocCreation();
        docCreation.setDocSourceTypeId(DocSourceType.Counter.value());
        docCreation.setParentDocId(313997);
        docCreation.setReceivedOriginalState(ReceivedOriginalState.ReceivedOriginal);
        docCreation.setDocSubject("Тест - 001");
        docCreation.setDocCaseLink(new DocCaseLinkDO());
        docCreation.setDocStatusProcessed(false);

        //TODO IF DIRECTION
        docCreation.setDocTypeId(165);
        docCreation.setDocDirection(DocDirection.Outgoing);


        List<DocCorrespondent> docCorrespondents = createDocCorrespondents();
        docCreation.setCorrespondents(docCorrespondents);

        Doc document = abdocsAdminService.createDocument(docCreation);
        Assert.assertNotNull(document);
    }

    public DocCreation generateDocCreationObject() {
        DocCreation docCreation = new DocCreation();
        docCreation.setRegistration(new RegistrationDto());
        docCreation.setDocSourceTypeId(DocSourceType.Counter.value());
        docCreation.getRegistration().setDocRegistrationType(DocRegistrationType.ByDocType);
        docCreation.setDocTypeId(155);
        docCreation.setDocSubject("Тест - 001");
        docCreation.setDocCaseLink(new DocCaseLinkDO());
        docCreation.setReceivedOriginalState(ReceivedOriginalState.WaitingForOriginal);

        List<DocCorrespondent> docCorrespondents = createDocCorrespondents();
        docCreation.setCorrespondents(docCorrespondents);
        return docCreation;
    }

    public DocCreation generateOutputDocument(Doc parentDoc) {
        DocCreation docCreation = new DocCreation();
        docCreation.setDocDirection(DocDirection.Outgoing);
        docCreation.setRegistration(null);
        docCreation.setDocStatusProcessed(false);

        docCreation.setDocCaseLink(new DocCaseLinkDO());
        docCreation.setDocSourceTypeId(DocSourceType.Counter.value());
        docCreation.setParentDocId(parentDoc.getDocId());
        docCreation.setReceivedOriginalState(ReceivedOriginalState.ReceivedOriginal);
        docCreation.setDocSubject("TEST");
        docCreation.setDocTypeId(165);

        List<DocCorrespondent> docCorrespondents = createDocCorrespondents();
        docCreation.setCorrespondents(docCorrespondents);

        List<AdditionalDocUnit> units = new ArrayList<>();

        AdditionalDocUnit fromUnit = new AdditionalDocUnit();
        fromUnit.setUsername("rayaan");
        fromUnit.setRole(DocUnitRole.From);
        units.add(fromUnit);

        AdditionalDocUnit editorUnit = new AdditionalDocUnit();
        editorUnit.setUsername("denislav.veizov");
        editorUnit.setRole(DocUnitRole.Editors);
        units.add(editorUnit);

        docCreation.setAdditionalDocUnits(units);
        return docCreation;
    }

    private List<DocCorrespondent> createDocCorrespondents() {
        List<DocCorrespondent> correspondents = new ArrayList<>();

        Correspondent correspondentById = abdocsAdminService.getCorrespondentById(251324);
        correspondentById.setId(null);
        List<CorrespondentContact> correspondentContacts = correspondentById.getCorrespondentContacts();
        if (!CollectionUtils.isEmpty(correspondentContacts)) {
            for (CorrespondentContact correspondentContact : correspondentContacts) {
                correspondentContact.setId(null);
                correspondentContact.setCorrespondentId(null);
            }
        }

        DocCorrespondent docCorrespondent = new DocCorrespondent();
        docCorrespondent.setCorrespondent(correspondentById);
        docCorrespondent.setDocCorrespondentType(DocCorrespondentType.Applicant);
//        docCorrespondent.setDocDestinationTypeId(Desti);

        correspondents.add(docCorrespondent);
        return correspondents;
    }

    @Test
    @Disabled
    void updateRegDate() {
        abdocsAdminService.updateRegistrationDate(314408, LocalDateTime.now());
        System.out.println(1);
    }

    @Test
    @Disabled
    public void createDocAction() {
        Integer documentId = 315687;
        Integer test = abdocsAdminService.selectAbdocsUserIdByUsername("rayaan");
        Integer pnikolov = abdocsAdminService.selectAbdocsUserIdByUsername("denislav.veizov");

        DocActionExpectedResult executionTargeting = DocActionExpectedResult.Coordination;

        DocActionRequest request = new DocActionRequest();
        request.setDocId(documentId);
        request.setType(executionTargeting.docActionTypes().get(0).value());
        request.setAddRootDocPermissions(true);
        request.setCreateDate(new Date());
        request.setUnitId(test);
        request.setFromUnitId(test);
        request.setExpectedResultId(executionTargeting.value());
        request.setNote("TST NOTE 2");


        DocUnit docUnit = new DocUnit();
        docUnit.setCreateDate(new Date());
        docUnit.setUnitId(pnikolov);
        docUnit.setDocUnitRole(DocUnitRole.To.value());
        request.setDocUnits(Collections.singletonList(docUnit));

        abdocsAdminService.insertDocAction(request);
    }
}
