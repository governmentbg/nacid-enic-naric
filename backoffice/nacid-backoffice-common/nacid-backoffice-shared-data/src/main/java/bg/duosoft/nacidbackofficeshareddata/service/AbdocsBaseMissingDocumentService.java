package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocCreation;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
public abstract class AbdocsBaseMissingDocumentService {

    protected abstract AbdocsAdminService getAbdocsService();

    protected abstract DocCreation getDocCreation(Integer applicationId);

    public Doc generateDocument(ApplicationDTO application) {
        DocCreation docCreation = getDocCreation(application.getId());

        String requestRegNumber = docCreation.getRegistration().getRegistrationNumber();
        Date requestEntryDate = docCreation.getRegistration().getRegDate();
        String registrationNumber = AbdocsNumbersUtils.buildRegistrationNumber(requestRegNumber, DateUtils.convertToLocalDate(requestEntryDate));

        Integer existingDocument = getAbdocsService().getDocumentIdByRegNumber(registrationNumber);
        if (Objects.nonNull(existingDocument)) {
            return null;
        }

        Doc document = getAbdocsService().createDocument(docCreation);

        try {
            LocalDate entryDate = application.getEntryDate();
            LocalDateTime createdDate = application.getDateCreated();
            if (entryDate.isEqual(createdDate.toLocalDate())) {//Abdocs uses registration date to form the registration number
                getAbdocsService().updateRegistrationDate(document.getDocId(), createdDate);
            } else {
                getAbdocsService().updateRegistrationDate(document.getDocId(), entryDate.atStartOfDay());
            }
        } catch (Exception e) {
            log.error("[ABDOCS - REGISTRATION DATE] Cannot change registration date of the document with registration number {}", registrationNumber);
            throw e;
        }

        return document;
    }

    protected ApplicationDTO getApplicationRecord(Integer applicationId, BaseApplicationService baseApplicationService) {
        ApplicationDTO application = baseApplicationService.selectApplicationById(applicationId);
        if (Objects.isNull(application)) {
            throw new RuntimeException("Cannot find documnet with application id = " + applicationId);
        }

        return application;
    }

}
