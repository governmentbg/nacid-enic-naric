package bg.duosoft.nacidservicesbe.validation.documentdelivery;

import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicEntryDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.FileValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 12:19
 */
@Component
@RequiredArgsConstructor
public class DocBibliographicDetailsValidator implements Validator<DocBibliographicDetailsDTO> {

    private final FileValidator fileValidator;

    @Override
    public List<ValidationError> validate(DocBibliographicDetailsDTO details, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if(details.getEntries() == null || details.getEntries().size() == 0 || details.getEntries().stream().filter(e -> !e.isForRemoval()).count() == 0){
            errors.add(ValidationError.builder().pointer("entries").message(ValidationMessageCodes.ADD_BIBLIOGRAPHIC_ENTRY).build());
        } else {
            details.getEntries().stream().filter(entry -> !entry.isForRemoval()).forEach(entry -> {
                if (!entryIsValid(entry)) {
                    errors.add(ValidationError.builder().pointer("entries").message(ValidationMessageCodes.BAD_VALUE_IN_ARRAY_CODE).build());
                }
            });
        }
        return errors;
    }

    public boolean entryIsValid(DocBibliographicEntryDetailsDTO entry){
        if(!(Boolean.TRUE.equals(entry.getBgLibraries()) || Boolean.TRUE.equals(entry.getElectronicCatalogues()) || Boolean.TRUE.equals(entry.getForeignLibraries()))){
            return false;
        }
        if(entry.getDeliveryResultKind() == null || !StringUtils.hasText(entry.getDeliveryResultKind().getId())){
            return false;
        }
        if(!fileValidator.fileIsValid(entry.getFile())){
            return false;
        }
        return true;
    }
}
