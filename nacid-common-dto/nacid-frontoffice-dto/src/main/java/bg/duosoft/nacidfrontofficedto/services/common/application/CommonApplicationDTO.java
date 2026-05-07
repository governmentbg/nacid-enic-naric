package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.ApplicationReceiptDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.08.2022
 * Time: 13:31
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CommonApplicationDTO {

    private Integer id;
    private CommonApplicantDetailsDTO applicantDetails;
    private DocumentDetailsDTO documentDetails;

    private String entryNumber;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate entryDate;
    private String tempNumber;
    private String accessCode;

    private FoApplicationStatus foStatus;
    private String lastStatusName;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_TIME_FORMAT)
    private LocalDateTime dateCreated;
    private LocalDateTime lastSubmissionDate;
    private String userCreated;

    private List<ApplicationReceiptDTO> receipts;
    private List<AppStatusHistoryDTO> statusHistory;

    private List<ApplicationMultipleRecordDTO> appsFromMultiple;

    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private String applicationSubtypeName;

    public Boolean getSubmittedOrFinalized(){
        if(statusHistory != null){
            return statusHistory.stream().filter(st -> st.getFoStatus() != null && (
                    st.getFoStatus().equals(FoApplicationStatus.SUBMITTED)
                            || st.getFoStatus().equals(FoApplicationStatus.FINALIZED))).count() >0 ;
        }
        return false;
    }
}
