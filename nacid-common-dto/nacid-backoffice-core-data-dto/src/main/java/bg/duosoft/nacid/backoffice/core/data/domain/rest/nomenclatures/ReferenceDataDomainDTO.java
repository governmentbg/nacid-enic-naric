package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDataDomainDTO {
    private String domain;
    private String name;
    private Boolean isFoReplication;

    public ReferenceDataDomainDTO(String domain) {
        this.domain = domain;
    }
}
