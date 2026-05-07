package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.08.2023
 * Time: 11:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DmsOnlyFoReceiptSaveDTO {

    private Integer efilingId;
    private Integer dmsDocumentId;
}
