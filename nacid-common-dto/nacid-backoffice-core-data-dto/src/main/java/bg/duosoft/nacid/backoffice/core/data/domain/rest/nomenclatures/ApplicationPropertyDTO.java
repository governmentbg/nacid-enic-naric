package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 14.09.2022
 * Time: 17:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationPropertyDTO {
    private String id;
    private String value;
    private String description;
}
