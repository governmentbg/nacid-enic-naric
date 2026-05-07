package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveOptionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveOptionFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveOptionKindDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.SEApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.main.SEMainDataDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DocumentReceiveOptionUtils {


    public static DocumentReceiveOptionFormDTO getDocumentReceiveOptionAfterToMainDataSection(
            List<ApplicationDocumentReceiveOptionDTO> documentReceiveOptions, String optionKind) {

        if (CollectionUtils.isEmpty(documentReceiveOptions) || optionKind == null) {
            return null;
        }

        for (ApplicationDocumentReceiveOptionDTO option : documentReceiveOptions) {
            if (optionKind.equals(option.getOptionKind().getId())) {
                DocumentReceiveOptionFormDTO formDTO = new DocumentReceiveOptionFormDTO();
                formDTO.setReceiveOption(option);
                return formDTO;
            }
        }
        return null;
    }


    public static void fillDocumentReceiveOptionAfterOverride(ApplicationDTO target, DocumentReceiveOptionFormDTO documentReceiveOptionForm) {

        if (CollectionUtils.isEmpty(target.getDocumentReceiveOptions())) {
            target.setDocumentReceiveOptions(new ArrayList<>());
        }

        DocumentReceiveOptionKindDTO newKind =
                documentReceiveOptionForm.getReceiveOption().getOptionKind();

        target.getDocumentReceiveOptions().removeIf(
                existing -> newKind != null && newKind.getId().equals(existing.getOptionKind().getId())
        );

        ApplicationDocumentReceiveOptionDTO applicationDocumentReceiveOptionDTO = new ApplicationDocumentReceiveOptionDTO();
        applicationDocumentReceiveOptionDTO.setId(target.getId());
        applicationDocumentReceiveOptionDTO.setOptionKind(newKind);
        applicationDocumentReceiveOptionDTO.setDocumentReceiveOption(
                documentReceiveOptionForm.getReceiveOption().getDocumentReceiveOption());
        applicationDocumentReceiveOptionDTO.setDocumentRecipientAddress(
                documentReceiveOptionForm.getReceiveOption().getDocumentRecipientAddress());

        if (Boolean.FALSE.equals(applicationDocumentReceiveOptionDTO.getDocumentReceiveOption().getDocumentRecipient())) {
            applicationDocumentReceiveOptionDTO.setDocumentRecipientAddress(null);
        }

        target.getDocumentReceiveOptions().add(applicationDocumentReceiveOptionDTO);
    }

}
