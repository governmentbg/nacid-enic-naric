package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUserDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LibservApplicationsDTO {
    private Integer id;
    private String ateCode;
    private String aseCode;
    private String entryNum;
    private String efilingId;
    private LocalDate entryDate;
    private String applicantName;
    private String apnStatusName;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String docflowStatusName;
    private String responsibleUserName;
    private String applicantTitleBefore;
    private String applicantTitleAfter;
    private String brKeywords;
    private String brPeriodRetFrom;
    private String brPeriodRetTo;
    private String brResultKindName;
    private String brResultKindCode;
    private String brSearchTypeName;
    private String brSearchTypeCode;
    private String brSubject;
    private String inquiryKindName;
    private String inquiryKindCode;
    private String inquiryAim;
    private String inquiryPeriodFrom;
    private String inquiryPeriodTo;
    private String previousInquiry;
    private String officialNoteKindName;
    private String officialNoteKindCode;
    private ApplicationResponsibleUserDataDTO responsibleUserData;
    private Boolean paidFlag;
}
