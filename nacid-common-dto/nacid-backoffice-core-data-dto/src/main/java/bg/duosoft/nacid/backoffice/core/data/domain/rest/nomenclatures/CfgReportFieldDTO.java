package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CfgReportFieldDTO {
    private String id;

    private String description;

    private ReferenceDataDTO fieldType;

    public CfgReportFieldDTO(String id) {
        this.id = id;
    }
}
