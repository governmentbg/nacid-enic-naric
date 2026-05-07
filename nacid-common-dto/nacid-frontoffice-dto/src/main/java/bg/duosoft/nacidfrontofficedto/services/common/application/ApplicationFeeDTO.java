package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.03.2023
 * Time: 18:03
 */
@Data
@NoArgsConstructor
public class ApplicationFeeDTO {
    private String feeKey;
    private String feeName;
    private Double feeAmount;
    private String feeCurrency;
    private Double alternateFeeAmount;
    private String alternateFeeCurrency;
}
