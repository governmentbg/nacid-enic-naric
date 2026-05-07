package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.services.common.document.ApplicationReceiptDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2023
 * Time: 13:32
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofApostilleApplicationDTO {

    private Integer id;

    private RegprofApplicantDetailsDTO applicantDetails;
    private RegprofEducationDetailsDTO educationDetails;
    private DocumentDetailsDTO documentDetails;

    private LocalDateTime dateCreated;
    private LocalDateTime lastSubmissionDate;
    private String entryNumber;
    private LocalDate entryDate;
    private String tempNumber;

    private String externalSystemId;
    private String externalSystemDocumentId;
    private BigDecimal totalFeesAmount;
    private String feesCurrencyCode;
    private Boolean eSigned;
    private Boolean paid;
    private String paymentTypeCode;
}
