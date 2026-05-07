package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocCreation;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.AbdocsMissingDocumentService;
import bg.duosoft.nacid.backoffice.rudi.be.util.reception.DocCreationConverter;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsBaseMissingDocumentService;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsMissingDocumentServiceImpl extends AbdocsBaseMissingDocumentService implements AbdocsMissingDocumentService {

    private final DocCreationConverter docCreationConverter;
    private final BaseApplicationService applicationService;
    private final AbdocsAdminService abdocsAdminService;

    @Override
    public Doc generateDocument(Integer applicationId) {
        return generateDocument(getApplicationRecord(applicationId, this.applicationService));
    }

    @Override
    protected AbdocsAdminService getAbdocsService() {
        return this.abdocsAdminService;
    }

    @Override
    protected DocCreation getDocCreation(Integer applicationId) {
        ApplicationDTO application = getApplicationRecord(applicationId, this.applicationService);
        return docCreationConverter.convertObjectForMissingDoc(application);
    }
}
