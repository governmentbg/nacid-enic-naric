package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidfrontofficedto.services.serecognition.SERecognitionKind;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 13:07
 */
@Data
public class ApplicationListRecordDTO {

    private Integer id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_TIME_FORMAT)
    private LocalDateTime dateCreated;
    private String userCreated;
    private LocalDateTime lastSubmissionDate;
    private String tempNumber;
    private String entryNumber;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate entryDate;

    private ApplicationSubtype applicationSubtype;
    private String applicantName;
    private String representativeName;
    private FoApplicationStatus foStatus;
    private String foStatusName;
    private String lastStatusName;
    private Boolean statute;
    private Boolean authenticity;
    private Boolean recommendation;
    private SERecognitionKind seRecognitionKind;
    private Boolean signed;
    private Boolean paid;
    private Boolean reverted;
    private String externalSystemId;

    private InquiryKind inquiryKind;
    private OfficialNoteKind officialNoteKind;
    private Boolean nacidSearch;
    private Boolean foreignSearch;
    private Integer boApnId;
    private Integer notesCount;
    private String serviceTypeId;
    private Boolean seOriginalDocumentWaitingFlag;
    private Boolean multipleApplication;
    private Boolean multipleAppUserActionRequired;

    public Boolean getRevertedAndDraftOrFinalized(){
        if(Boolean.TRUE.equals(reverted) && (FoApplicationStatus.DRAFT.equals(foStatus)
                || FoApplicationStatus.FINALIZED.equals(foStatus))){
            return true;
        }
        return false;
    }
    public Boolean getRevertedAndNotAccepted(){
        if(Boolean.TRUE.equals(reverted)
                && !FoApplicationStatus.ACCEPTED.equals(foStatus)){
            return true;
        }
        return false;
    }

}
