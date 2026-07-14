package bg.duosoft.nacid.backoffice.core.be.validation.common;

import bg.duosoft.nacid.payments.dto.payments.FeeCalculationResponse;
import bg.duosoft.nacid.payments.dto.payments.FeeInsertionResponse;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertFeesResponseValidator implements Validator<FeeInsertionResponse> {
    @Override
    public List<ValidationError> validate(FeeInsertionResponse insertFeesResponse, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if (Objects.nonNull(insertFeesResponse) && Objects.nonNull(insertFeesResponse.getCalculationResponse())) {
            FeeCalculationResponse calculationResponse = insertFeesResponse.getCalculationResponse();
            rejectIfTrue(errors, calculationResponse.isForApproval(), "insertFeesApproval", "m.validation.insert.fees.need.approval");
        }
        return errors;
    }
}
