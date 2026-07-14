package bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidservicesclient.client.ServicesUniChecksClient;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SarAcceptanceValidator implements Validator<RudiApplicationDTO> {

    private final SarReceptionValidator sarReceptionValidator;
    private final ServicesUniChecksClient servicesUniChecksClient;

    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = sarReceptionValidator.validate(rudiApplicationDTO);
        validateRepresentative(rudiApplicationDTO, errors);
        rejectIfEmpty(errors, rudiApplicationDTO.getTrainingCourse().getDiplomaOwner(), "diplomaOwner", "validation.field.required");
        return errors;
    }

    private void validateRepresentative(RudiApplicationDTO rudiApplicationDTO, List<ValidationError> errors) {
        PersonDTO representative = rudiApplicationDTO.getApplication().getRepresentative();
        if (Objects.isNull(representative)) {
            Integer efilingId = rudiApplicationDTO.getApplication().getEfilingId();
            UniChecksApplicationDTO application = servicesUniChecksClient.getApplication(efilingId);
            if (Objects.isNull(application)) {
                log.error("Cannot find front-office application with efilingId = " + efilingId);
                throw new RuntimeException("Cannot find front-office application with efilingId = " + efilingId);
            }

            NaturalPersonDTO foRepresentative = application.getApplicantDetails().getRepresentative();
            if (Objects.nonNull(foRepresentative)) {
                reject(errors, "representative", "validation.field.required");
            }
        }
    }

}
