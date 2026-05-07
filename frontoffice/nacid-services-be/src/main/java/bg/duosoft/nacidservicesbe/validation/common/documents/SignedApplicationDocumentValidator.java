package bg.duosoft.nacidservicesbe.validation.common.documents;

import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.02.2023
 * Time: 16:15
 */
@Component
public class SignedApplicationDocumentValidator implements Validator<SignedApplicationDocumentDTO> {

    @Override
    public List<ValidationError> validate(SignedApplicationDocumentDTO signed, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmpty(errors, signed.getFile(), "file", ValidationMessageCodes.REQUIRED_CODE);
        if(signed.getFile() != null){
            rejectIfEmptyString(errors, signed.getFile().getFileId(), "file.fileId", ValidationMessageCodes.SELECT_FILE_CODE);
            if(StringUtils.hasText(signed.getFile().getFileId())) {
                if(!StringUtils.hasText(signed.getFile().getRelativePath()) || !StringUtils.hasText(signed.getFile().getRootDirectory()) || !StringUtils.hasText(signed.getFile().getFileName())) {
                    reject(errors, "file.fileId", ValidationMessageCodes.INVALID_FILE_CODE);
                }
            }
        }
        return errors;
    }
}
