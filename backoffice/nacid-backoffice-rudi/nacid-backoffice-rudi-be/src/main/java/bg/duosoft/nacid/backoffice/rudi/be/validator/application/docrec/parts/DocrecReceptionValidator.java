package bg.duosoft.nacid.backoffice.rudi.be.validator.application.docrec.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocumentReceiveMethod;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.RudiReceptionBaseValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocrecReceptionValidator extends RudiReceptionBaseValidator implements Validator<RudiApplicationDTO> {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = super.validate(rudiApplicationDTO);

        ApplicationDTO application = rudiApplicationDTO.getApplication();
        rejectIfEmpty(errors, application.getPersonalDocumentType(), "personalDocumentTypeId", "validation.field.required");

        validateCertificateReceiveMethod(application, errors);
        return errors;
    }

}
