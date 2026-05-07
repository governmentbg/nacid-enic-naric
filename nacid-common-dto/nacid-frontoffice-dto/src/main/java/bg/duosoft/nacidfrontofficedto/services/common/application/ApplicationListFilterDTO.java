package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.TextSearchType;
import bg.duosoft.nacidfrontofficedto.services.serecognition.SERecognitionKind;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 15:42
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationListFilterDTO {

    public static final String SUBMITTED_COMBINED_STATUS_VALUE = "SUBMITTED_COMBINED";

    private String user;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private LocalDate dateLastSubmittedFrom;
    private LocalDate dateLastSubmittedTo;
    private String foStatusSelectValue;
    private List<FoApplicationStatus> foStatuses;
    private List<FoApplicationStatus> foStatusesExclude;
    private String lastStatusName;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private String entryNumber;
    private LocalDate entryDateFrom;
    private LocalDate entryDateTo;
    private Boolean statute;
    private Boolean authenticity;
    private Boolean recommendation;
    private SERecognitionKind seRecognitionKind;
    private String applicantName;
    private TextSearchType applicantNameSearchType;
    private String representativeName;
    private TextSearchType representativeNameSearchType;
    private String applicantCivilId;
    private Boolean signed;
    private Boolean paid;
    private Boolean skipAccepted;
    private Boolean originalDocumentWaitingFlag;

    private Integer page;
    private Integer pageSize;
    private String order;
    private String orderBy;
}
