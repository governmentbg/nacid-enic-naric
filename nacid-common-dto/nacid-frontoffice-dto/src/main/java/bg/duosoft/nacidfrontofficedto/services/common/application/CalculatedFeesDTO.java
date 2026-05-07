package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.04.2023
 * Time: 10:51
 */
@Data
public class CalculatedFeesDTO {
    private Boolean forApproval;
    private String currencyCode;
    private String alternateCurrencyCode;
    private List<ApplicationFeeDTO> fees;
    private Double total;
    private Double totalAlternateCurrency;
}
