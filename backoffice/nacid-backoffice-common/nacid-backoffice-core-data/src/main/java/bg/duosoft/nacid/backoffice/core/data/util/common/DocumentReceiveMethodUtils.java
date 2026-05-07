package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentReceiveMethodUtils {

    public static ApplicationDocumentReceiveMethodDTO convertToCommonDocumentReceiveMethod(List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods) {
        return !CollectionUtils.isEmpty(documentReceiveMethods) ? documentReceiveMethods.get(0) : null;
    }

    public static List<ApplicationDocumentReceiveMethodDTO> convertCommonDocumentReceiveMethodToList(ApplicationDocumentReceiveMethodDTO documentReceiveMethod) {
        if (Objects.isNull(documentReceiveMethod)) {
            return new ArrayList<>();
        }

        AddressDTO documentRecipientAddress = documentReceiveMethod.getDocumentRecipientAddress();
        if (Objects.nonNull(documentRecipientAddress) && Objects.isNull(documentRecipientAddress.getId())) {
            documentReceiveMethod.setDocumentRecipientAddress(null);
        }

        return List.of(documentReceiveMethod);
    }

    public static DocumentReceiveMethodFormDTO convertToDocumentReceiveMethodForm(List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods) {
        if (!CollectionUtils.isEmpty(documentReceiveMethods)) {
            DocumentReceiveMethodFormDTO documentReceiveMethodFormDTO = new DocumentReceiveMethodFormDTO();
            documentReceiveMethodFormDTO.setCrfCodes(new ArrayList<>());
            documentReceiveMethods.forEach(method -> {
                boolean isPaper = method.getCrfCode().getId().equals(CertificateReceiveFormType.PAPER.code());
                if (isPaper) {
                    documentReceiveMethodFormDTO.setPaperReceivedMethod(method);
                } else {
                    documentReceiveMethodFormDTO.setElectronicReceivedMethod(method);
                }
                documentReceiveMethodFormDTO.getCrfCodes().add(method.getCrfCode().getId());
            });
            return documentReceiveMethodFormDTO;
        }
        return null;
    }

    public static List<ApplicationDocumentReceiveMethodDTO> convertToApplicationDocumentReceiveMethod
            (DocumentReceiveMethodFormDTO documentReceiveMethodForm) {
        if (Objects.nonNull(documentReceiveMethodForm) && !CollectionUtils.isEmpty(documentReceiveMethodForm.getCrfCodes())) {
            List<ApplicationDocumentReceiveMethodDTO> finalResult = new ArrayList<>();
            documentReceiveMethodForm.getCrfCodes().forEach(crfCode -> {
                boolean isPaper = crfCode.equals(CertificateReceiveFormType.PAPER.code());
                if (isPaper) {
                    documentReceiveMethodForm.getPaperReceivedMethod().setCrfCode(new ReferenceDataDTO(ReferenceDataDomain.CERTIFICATE_RECEIVE_FORM.domain(), CertificateReceiveFormType.PAPER.code()));
                    finalResult.add(documentReceiveMethodForm.getPaperReceivedMethod());
                } else {
                    documentReceiveMethodForm.getElectronicReceivedMethod().setCrfCode(new ReferenceDataDTO(ReferenceDataDomain.CERTIFICATE_RECEIVE_FORM.domain(), CertificateReceiveFormType.ELECTRONIC.code()));
                    finalResult.add(documentReceiveMethodForm.getElectronicReceivedMethod());
                }
            });
            finalResult.forEach(fr -> {
                if (fr.getDocumentRecipientAddress() != null && fr.getDocumentRecipientAddress().getId() == null) {
                    fr.setDocumentRecipientAddress(null);
                }
            });
            return finalResult;
        }
        return new ArrayList<>();
    }
}
