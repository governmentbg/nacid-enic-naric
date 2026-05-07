package bg.duosoft.nacidbackofficeshareddata.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateDocTypes;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocTypes;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocTypes.OFFICIAL_NOTE_SECONDARY;


//TODO N22-393 This has to be tested !
public abstract class BaseApplicationReportValidator implements Validator<ApplicationDTO> {
    protected static final String DOCUMENT_TYPE_POINTER = "documentType.id";

    @Override
    public List<ValidationError> validate(ApplicationDTO application, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        AttachedDocDTO attachment = (AttachedDocDTO) args[0];
        Integer documentTypeId = attachment.getDocumentType().getId();
        Integer attachmentId = attachment.getId();
        Optional<CertificateDocTypes> cdt = CertificateDocTypes.getCertificateDocTypes(ApplicationType.selectByCode(application.getApplicationType().getId()), ApplicationSubType.selectByTypeAndSubType(application.getApplicationType().getId(), application.getApplicationSubtype().getId()));
        if (cdt.isPresent() && cdt.get().isAnyDocType(documentTypeId)) {
            validateOnEditAttachment(errors, application, documentTypeId, attachmentId);
            validateOnCreateAttachment(errors, application, cdt.orElse(null), attachmentId, documentTypeId);
        }
        customValidations(errors, application, documentTypeId, attachmentId);
        return errors;
    }

    protected void customValidations(List<ValidationError> errors, ApplicationDTO application, Integer docTypeId, Integer attachmentId) {

    }
    private void validateOnEditAttachment(List<ValidationError> errors, ApplicationDTO application, Integer docTypeId, Integer attachmentId) {
        List<AttachedDocDTO> attachments = application.getAttachments();
        if (Objects.nonNull(attachmentId) && !CollectionUtils.isEmpty(attachments)) {
            AttachedDocDTO existedAttachment = attachments.stream().filter(r -> r.getId().equals(attachmentId)).findFirst().orElse(null);
            if (Objects.nonNull(existedAttachment) && Objects.nonNull(existedAttachment.getDocumentType())) {
                rejectIfTrue(errors, !existedAttachment.getDocumentType().getId().equals(docTypeId), DOCUMENT_TYPE_POINTER, "validation.forbidden.document.type.change");
            }
        }
    }

    private void validateOnCreateAttachment(List<ValidationError> errors, ApplicationDTO application, CertificateDocTypes cdt, Integer attachmentId, Integer docTypeId) {
        if (Objects.isNull(attachmentId)) {
            if (Objects.equals(docTypeId, cdt.getCertificateDocTypeId())) {
                validateCertificate(errors, application);
            } else if (Objects.equals(docTypeId, cdt.getObviousMistakeDocTypeId())) {
                validateCertificateObviousMistake(errors, application, cdt);
            } else if (Objects.equals(docTypeId, cdt.getDuplicateDocTypeId())) {
                validateCertificateDuplicate(errors, application, cdt);
            }
        }
    }


    //Certificate duplicate validations
    private void validateCertificateDuplicate(List<ValidationError> errors, ApplicationDTO application, CertificateDocTypes cdt) {
        validateCertificateDuplicatePreviousDocuments(errors, application, cdt);
    }

    private void validateCertificateDuplicatePreviousDocuments(List<ValidationError> errors, ApplicationDTO application, CertificateDocTypes cdt) {
        List<AttachedDocDTO> attachments = application.getAttachments();
        if (!CollectionUtils.isEmpty(attachments)) {
            AttachedDocDTO certificateAttachment = attachments.stream().filter(r -> cdt.isAnyDocType(r.getDocumentType().getId())).findFirst().orElse(null);
//            AttachedDocDTO certificateDuplicateRequest = attachments.stream().filter(r -> DocTypes.CERTIFICATE_DUPLICATE_REQUEST.code().equals(r.getDocumentType().getId())).findFirst().orElse(null);
            rejectIfTrue(errors, Objects.isNull(certificateAttachment), DOCUMENT_TYPE_POINTER, getMissingCertificateLabel(cdt));
//            rejectIfTrue(errors, Objects.isNull(certificateDuplicateRequest), DOCUMENT_TYPE_POINTER, "validation.missing.certificate.duplicate.request");
        } else {
            rejectIfTrue(errors, true, DOCUMENT_TYPE_POINTER, getMissingCertificateLabel(cdt));
//            rejectIfTrue(errors, true, DOCUMENT_TYPE_POINTER, "validation.missing.certificate.duplicate.request");
        }
    }
    ////////////////////////////////////////////////

    //Certificate obvious mistake validations
    private void validateCertificateObviousMistake(List<ValidationError> errors, ApplicationDTO application, CertificateDocTypes cdt) {
        validateCertificateObviousMistakePreviousDocuments(errors, application, cdt);
    }

    private void validateCertificateObviousMistakePreviousDocuments(List<ValidationError> errors, ApplicationDTO application, CertificateDocTypes cdt) {
        List<AttachedDocDTO> attachments = application.getAttachments();
        if (!CollectionUtils.isEmpty(attachments)) {
            AttachedDocDTO certificateAttachment = attachments.stream().filter(r -> cdt.isAnyDocType(r.getDocumentType().getId())).findFirst().orElse(null);
//            AttachedDocDTO obviousMistakeRequest = attachments.stream().filter(r -> DocTypes.CERTIFICATE_OBVIOUS_MISTAKE_REQUEST.code().equals(r.getDocumentType().getId())).findFirst().orElse(null);
            rejectIfTrue(errors, Objects.isNull(certificateAttachment), DOCUMENT_TYPE_POINTER, getMissingCertificateLabel(cdt));
//            rejectIfTrue(errors, Objects.isNull(obviousMistakeRequest), DOCUMENT_TYPE_POINTER, "validation.missing.obvious.mistake.request");
        } else {
            rejectIfTrue(errors, true, DOCUMENT_TYPE_POINTER, getMissingCertificateLabel(cdt));
//            rejectIfTrue(errors, true, DOCUMENT_TYPE_POINTER, "validation.missing.obvious.mistake.request");
        }
    }
    ////////////////////////////////////////////////

    //Certificate validations
    public abstract void validateCertificate(List<ValidationError> errors, ApplicationDTO application);

    public AttachedDocDTO initAttachmentBeforeValidation(Integer documentTypeId, Integer attachmentId) {
        AttachedDocDTO attachedDocDTO = new AttachedDocDTO();
        DocumentTypeDTO documentType = new DocumentTypeDTO();
        attachedDocDTO.setId(attachmentId);
        documentType.setId(documentTypeId);
        attachedDocDTO.setDocumentType(documentType);
        return attachedDocDTO;
    }

    private String getMissingCertificateLabel(CertificateDocTypes cdt) {
        return switch (DocTypes.selectByCode(cdt.getCertificateDocTypeId())) {
            case OFFICIAL_NOTE_SECONDARY -> "validation.missing.official.note";
            case VERIFICATION_LETTER ->  "validation.missing.verification.letter";
            default -> "validation.missing.certificates";
        };
    }

    ////////////////////////////////////////////////
}
