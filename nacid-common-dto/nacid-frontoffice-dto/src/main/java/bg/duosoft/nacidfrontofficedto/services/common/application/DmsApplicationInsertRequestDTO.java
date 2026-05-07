package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.document.AttachedDocumentDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.08.2023
 * Time: 18:21
 */
@Data
public class DmsApplicationInsertRequestDTO {

    private Integer id;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private String entryNumber;
    private LocalDate entryDate;
    private List<AttachedDocumentDTO> attachments;
}
