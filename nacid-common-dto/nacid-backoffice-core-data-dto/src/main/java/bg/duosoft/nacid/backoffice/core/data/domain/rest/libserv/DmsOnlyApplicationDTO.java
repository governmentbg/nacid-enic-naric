package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.08.2023
 * Time: 14:00
 */
@Data
public class DmsOnlyApplicationDTO {

    private Integer efilingId;
    private String applicationTypeCode;
    private String applicationSubtypeCode;
    private PersonDTO applicant;
    private PersonDTO representative;
    private AddressDTO contactAddress;
    private List<AttachedDocDTO> attachments;

    private String entryNumber;
    private LocalDate entryDate;
}
