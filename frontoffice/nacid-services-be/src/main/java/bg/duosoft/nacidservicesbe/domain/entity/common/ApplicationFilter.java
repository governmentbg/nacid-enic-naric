package bg.duosoft.nacidservicesbe.domain.entity.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 15:48
 */
@Getter
@Setter
public class ApplicationFilter {

    private String user;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private LocalDate dateLastSubmittedFrom;
    private LocalDate dateLastSubmittedTo;
    private List<String> foStatusCodes;
    private List<String> foStatusCodesExclude;
    private String lastStatusName;
    private String applicationTypeCode;
    private String applicationSubtypeCode;
    private String tempNumber;
    private String entryNumber;
    private LocalDate entryDateFrom;
    private LocalDate entryDateTo;
    private boolean applicationSAR;
    private Boolean statute;
    private Boolean authenticity;
    private Boolean recommendation;
    private String applicantName;
    private Boolean signed;
    private Boolean paid;

    private Integer page;
    private Integer pageSize;
    private String order;
    private String orderBy;
}
