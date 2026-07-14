package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 17:29
 */
@Data
@NoArgsConstructor
public class CfgGraduationWayToAppTypeDTO {
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private ReferenceDataDTO graduationWay;
    public CfgGraduationWayToAppTypeDTO(String ateCode, String aseCode, String gwyCode) {
        this.applicationType = new ApplicationTypeDTO(ateCode);
        this.applicationSubtype = new ApplicationSubtypeDTO(aseCode);
        this.graduationWay = new ReferenceDataDTO(ReferenceDataDomain.GRADUATION_WAY.domain(), gwyCode);
    }
}
