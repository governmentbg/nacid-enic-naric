package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documenttype.DocumentTypeDetailsClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocTypes;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAppStatusDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalReportValidator {
    @Autowired
    private DocumentTypeDetailsClient documentTypeDetailsClient;

    public void validate(List<RudiApplicationDTO> applications, DocumentTypeDTO documentType) {
        if (!documentType.getId().equals(DocTypes.CERTIFICATE.code()) && !documentType.getId().equals(DocTypes.LETTER_TO_APPLICANT.code()) && !documentType.getId().equals(DocTypes.REJECTION_DECISION.code())) {
            throw new InternalServerErrorException("Invalid doc type!");
        }
        List<ValidationError> errors = new ArrayList<>();

        for (RudiApplicationDTO application : applications) {

            String applicationStatus = application.getApplication().getStatus().getId();
            List<DocumentTypeAppStatusDetailDTO> docTypeStatuses = documentType.getStatuses();
            if (CollectionUtils.isEmpty(docTypeStatuses)) {
                errors.add(ValidationError.builder().pointer("attachment").message("validation.incorrect.application.status").build());
                break;
            }

            DocumentTypeAppStatusDetailDTO statusConfiguration = docTypeStatuses.stream().filter(r -> r.getStatus().getId().equals(applicationStatus)).findFirst().orElse(null);
            if (Objects.isNull(statusConfiguration)) {
                errors.add(ValidationError.builder().pointer("attachment").message("validation.incorrect.application.status").build());
                break;
            }

            if (documentType.getId().equals(DocTypes.CERTIFICATE.code())) {
                List<ApplicationCertificatesDTO> certificates = application.getApplication().getCertificates();
                if (!CollectionUtils.isEmpty(certificates)) {
                    ApplicationCertificatesDTO publishedCertificate = certificates.stream().filter(r -> r.getCertificateStatus().equals(ReferenceDataCode.CERTIFICATE_STATUS_PUBLISHED.code())).findFirst().orElse(null);
                    if (Objects.nonNull(publishedCertificate)) {
                        errors.add(ValidationError.builder().pointer("attachment").message("validation.already.generated.certificate").build());
                        break;
                    }
                }
            }

            if (documentType.getId().equals(DocTypes.REJECTION_DECISION.code())) {
                AttachedDocDTO rejectionDecision = application.getApplication().getAttachmentsByDocTypes(DocTypes.REJECTION_DECISION.code()).stream().findFirst().orElse(null);
                if (Objects.nonNull(rejectionDecision)) {
                    errors.add(ValidationError.builder().pointer("attachment").message("validation.already.generated.rejection.decision").build());
                    break;
                }
            }

            checkTemplates(errors, documentType, application.getApplication().getId());
        }
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }
    }


    private void checkTemplates(List<ValidationError> errors, DocumentTypeDTO documentType, Integer applicationId) {
        List<DocumentTypeDetailDTO> details = documentType == null ? null : documentTypeDetailsClient.selectDocumentTypeDetails(applicationId, DocCategory.APP_ATTACHMENTS.code(), documentType.getId());
        if (CollectionUtils.isEmpty(details)) {
            errors.add(ValidationError.builder().pointer("attachment").message("validation.no.templates").build());
        }
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }
    }
}
