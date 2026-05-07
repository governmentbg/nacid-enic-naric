package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 20.07.2022
 * Time: 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalNomenclaturesMapDTO {
    private Integer id;
    private String system;
    private String nomenclatureType;
    private String internalNomId;
    private String condition1;
    private String condition2;
    private String externalNomId;

    @JsonIgnore
    public Integer getExternalNomIdAsInteger() {
        return externalNomId == null || "".equals(externalNomId) ? null : Integer.parseInt(externalNomId);
    }
}
