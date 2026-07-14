package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidfrontofficedto.services.serecognition.SERecognitionKind;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.04.2023
 * Time: 14:18
 */
@Data
public class ApplicationMultipleRecordDTO {

    private Integer id;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private String tempNumber;
    private String entryNumber;
    private LocalDate entryDate;
    private Integer multipleApplicationId;

    private InquiryKind inquiryKind;
    private OfficialNoteKind officialNoteKind;
    private Boolean nacidSearch;
    private Boolean foreignSearch;
    private SERecognitionKind seRecognitionKind;
}
