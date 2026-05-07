package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.client.client.common.applicantattacheddocs.AdminApplicationAttachedDocsClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.externalnomenclaturesmap.AdminExternalNomenclaturesMapClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.RasService;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

import static bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureSystem.RAS;

/**
 * User: ggeorgiev
 * Date: 05.07.2023
 * Time: 13:13
 */
@Component
@RequiredArgsConstructor
public class RegisterRasApplicationValidator implements Validator<RudiApplicationDTO> {
    private final AdminApplicationAttachedDocsClient applicationAttachedDocsClient;
    @Getter
    public static class CertFileHolder {
        private Integer certificateFileId;

        public CertFileHolder(Integer certificateFileId) {
            this.certificateFileId = certificateFileId;
        }

        private DownloadFileResponse certFile;
    }

    private final AbdocsAdminService abdocsAdminService;
    private final AdminExternalNomenclaturesMapClient externalNomenclaturesMapClient;
    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplication, Object... args) {
        List<ValidationError> errors = initErrorList();
        RasService rasService = (RasService) args[0];
        CertFileHolder certFileHolder = (CertFileHolder) args[1];
        ApplicationDTO baseApplication = rudiApplication.getApplication();
        if (rasService.isApplicationTransferredInRas(baseApplication.getId())) {
            reject(errors, "ras.application", "validation.rasRegister.alreadyTransferred");
            return errors;
        }

        rejectIfFalse(errors, Objects.equals(baseApplication.getApplicationSubtype().getId(), ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION.appSubType()), "ras.applicationSubtype", "validation.rasRegister.incorrectApplicationSubtype");
        if (errors.size() > 0) {
            return errors;
        }


        ApplicationCertificatesDTO cert = baseApplication
                .getCertificates()
                .stream()
                .filter(c -> Objects.equals(c.getCertificateStatus(), ReferenceDataCode.CERTIFICATE_STATUS_PUBLISHED.code()))
                .findFirst()
                .orElse(null);
        String fullCertNumber = cert == null ? null : cert.getCertificateNumber();
        if (ObjectUtils.isEmpty(fullCertNumber)) {
            reject(errors, "ras.certificateNumber", "validation.rasRegister.missingCertificateNumber");
        } else {
            String[] parts = fullCertNumber.split("/");
            if (parts.length != 2 || DateUtils.parseDate(parts[1]) == null) {
                reject(errors, "ras.certificateNumber", "validation.rasRegister.incorrectFormat");
            }
        }

        ApplicationRecognizedDetailsDTO recognitionDetails = rudiApplication.getApplicationRecognizedDetails();
        Integer rasResearchArea = recognitionDetails == null || recognitionDetails.getProfGroup() == null ? null : externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), ExternalNomenclatureType.RAS_PROF_GROUP.code(), recognitionDetails.getProfGroup().getId().toString());
        if (rasResearchArea == null) {
            reject(errors, "ras.professionGroup", "validation.rasRegister.missingProfessionGroupMapping");
        }
        String recognizedEduLevelId = recognitionDetails == null ? null : ObjectUtils.isEmpty(recognitionDetails.getRecognizedEduLevel()) ? null : recognitionDetails.getRecognizedEduLevel();
        if (recognizedEduLevelId == null) {
            reject(errors, "ras.recognizedEduLevel", "validation.rasRegister.missingRecognizedEduLevel");
        } else {
            Integer rasRecognizedEduLevelKey = externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), ExternalNomenclatureType.RAS_EDU_LEVEL.code(), recognizedEduLevelId);
            if (rasRecognizedEduLevelKey == null) {
                reject(errors, "ras.recognizedEduLevel", "validation.rasRegister.missingRecognizedEduLevelMapping");
            }
        }



        rejectIfTrue(errors, certFileHolder.certificateFileId == null, "ras.certificateAttachment", "validation.rasRegister.missingCertificateAttachment");
        if (certFileHolder.certificateFileId != null) {
            List<DocFile> certfiles = rasService.selectCertificateFiles(baseApplication.getId());
            DocFile certfile = certfiles == null ? null : certfiles.stream().filter(r -> Objects.equals(r.getId(), certFileHolder.certificateFileId)).findFirst().orElse(null);
            if (certfile == null) {
                reject(errors, "ras.certificateAttachment", "validation.rasRegister.missingAbdocsDocumentId");
            } else {
                DownloadFileResponse certFile = abdocsAdminService.downloadFile(certfile.getKey().toString(), certfile.getName(), certfile.getDbId());
                certFileHolder.certFile = certFile;
            }
        }

        return errors;
    }
}
