package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberStatementDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppCommissionMemberStatementDataValidator implements Validator<RudiApplicationDTO> {
    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rudiApplicationDTO.getApplicationCommissionMembers())) {
            for (ApplicationCommissionMemberStatementDTO statement : rudiApplicationDTO.getApplicationCommissionMemberStatements()) {
                rejectIfTrue(errors, Objects.isNull(statement.getCommissionMember()) || Objects.isNull(statement.getCommissionMember().getId()), "commissionMember.id", "validation.field.required");
                AttachedDocDTO attachedDoc = statement.getAttachedDoc();
                if (Objects.isNull(attachedDoc)) {
                    rejectIfTrue(errors, true, "attachedDoc.attachedDocAttachments", "attachedDocAttachments.empty");
                    rejectIfTrue(errors, true, "attachedDoc.documentType.id", "validation.field.required");
                } else {
                    rejectIfTrue(errors, Objects.isNull(attachedDoc.getDocumentType()) || Objects.isNull(attachedDoc.getDocumentType().getId()), "attachedDoc.documentType.id", "validation.field.required");
                    rejectIfTrue(errors, CollectionUtils.isEmpty(attachedDoc.getAttachedDocAttachments()), "attachedDoc.attachedDocAttachments", "attachedDocAttachments.empty");
                }


            }
        }
        return errors;
    }
}
