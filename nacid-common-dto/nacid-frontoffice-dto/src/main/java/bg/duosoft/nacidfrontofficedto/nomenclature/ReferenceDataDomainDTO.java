package bg.duosoft.nacidfrontofficedto.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.09.2022
 * Time: 13:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDataDomainDTO {

    private String domain;
    private String name;
    private Boolean foOnly;
}
