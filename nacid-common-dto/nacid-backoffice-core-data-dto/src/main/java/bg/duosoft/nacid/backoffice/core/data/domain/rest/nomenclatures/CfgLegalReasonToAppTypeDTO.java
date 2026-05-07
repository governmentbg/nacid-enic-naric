package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 30.05.2023
 */
@Data
@NoArgsConstructor
public class CfgLegalReasonToAppTypeDTO {
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    public CfgLegalReasonToAppTypeDTO(String ateCode, String aseCode) {
        this.applicationType = new ApplicationTypeDTO(ateCode);
        this.applicationSubtype = new ApplicationSubtypeDTO(aseCode);
    }
}
