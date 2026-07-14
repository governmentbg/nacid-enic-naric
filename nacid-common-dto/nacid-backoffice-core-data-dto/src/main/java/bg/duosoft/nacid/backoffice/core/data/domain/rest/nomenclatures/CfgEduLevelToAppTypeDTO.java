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
public class CfgEduLevelToAppTypeDTO {
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private ReferenceDataDTO educationLevel;
    public CfgEduLevelToAppTypeDTO(String ateCode, String aseCode, String ellCode) {
        this.applicationType = new ApplicationTypeDTO(ateCode);
        this.applicationSubtype = new ApplicationSubtypeDTO(aseCode);
        this.educationLevel = new ReferenceDataDTO(ReferenceDataDomain.EDUCATION_LEVEL.domain(), ellCode);
    }
}
