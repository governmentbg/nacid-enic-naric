package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {
    private Integer docId; // Id of the case. If doc is Case RootDocId = Id;
    private Integer rootDocId;
    private Integer parentId;
    private DocDirection docDirection;
    private Integer docTypeId;
    private Integer docSourceTypeId; // nullable
    private String docSubject;
    private DocStatus docStatus;
    private String regUri;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date regDate;
    private Integer receiptOrder;// Order of creation inside the case.
    private List<DocCorrespondent> docCorrespondents;
    private List<DocFile> docFiles;
    private ReceivedOriginalState receivedOriginalState; // Получен оригинал
}

//    private Integer tenantId; // nullable
//    private Guid guid; // This is needed for EgovExchange and Will be generated only for documants that participate in exchange, nullable
//    private Boolean isCase;
//    private String docBody; // Text section in the UI
//    private DocRegistrationType regType;
//    private Integer registerIndexId;
//    private String regIndex; // code of RegisterIndex
//    private Integer regSequenceNumber;// Value from RegisterIndexCounter used in filtering
//    private Integer caseRegSequenceNumber;// Value for Number in case for the current Register Index
//    private Boolean isRegistered;
//    private Boolean RegIncludeDateInNumber; // When register doc by case/parent and case/parent is registered with external number
//    private Date dateReceived; // The time in which document is handled to the applicant. Nullable
//    private String accessCode;
//    private Date assignmentDeadline;// nullable
//    private Boolean isSigned;
//    private Integer archiveIndexId; // nullable
//    private Integer archiveYear;// nullable
//    private Date deadlineDate; // nullable
//    private Integer gdprId;// nullable
//    private Integer activityId; // nullable
//    private String archiveFolderNumber;
//    private Integer archiveBoxNumber;// nullable
//    private Integer archiveCaseNumber;// nullable
//    private Date archiveDate; // nullable
//    private Integer incomingDocId;// nullable
//    private Integer IncomingEmailId; // nullable
//    private List<DocUnit> docUnits;
//    private List<DocFileLink> docFileLinks;
//    private List<DocClassification> docClassifications;
//    private List<DocHasRead> docHasReads;
//    private List<DocWorkflow> docWorkflows;
//    private List<DocCopy> docCopies;
//    private List<DocConnectedDoc> docConnectedDocs;
//    private List<DocComment> docComments;
//    private List<DocTextField> docTextFields;
//    private List<DocActionRequest> docActionRequests;
//    private List<DocEmail> docEmails;
//    private List<DocElectronicServiceStage> docElectronicServiceStages;
//    private DocElectronicServiceStage CurrentElectronicServiceStage;
//    private Integer officialDocId;// nullable
//    private String docIdHash;
//    private List<Doc> children;
//    private Integer level;
//    private Boolean hasChildren;
//    private List<DocClosure> descendants;
//    private List<DocClosure> ancestors;