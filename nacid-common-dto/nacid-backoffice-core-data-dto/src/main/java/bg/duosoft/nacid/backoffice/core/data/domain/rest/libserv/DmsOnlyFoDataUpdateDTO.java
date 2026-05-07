package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.08.2023
 * Time: 11:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DmsOnlyFoDataUpdateDTO {

    private Integer efilingId;
    private String entryNum;
    private LocalDate entryDate;
    private String initiatingUser;
    private Integer dmsDocumentId;

}
