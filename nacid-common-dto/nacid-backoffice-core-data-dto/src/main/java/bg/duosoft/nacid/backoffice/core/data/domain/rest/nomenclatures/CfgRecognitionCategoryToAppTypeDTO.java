package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 30.05.2023
 */
@Data
@NoArgsConstructor
public class CfgRecognitionCategoryToAppTypeDTO {
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private ReferenceDataDTO recognitionCategory;
    public CfgRecognitionCategoryToAppTypeDTO(String ateCode, String aseCode, String rcyCode) {
        this.applicationType = new ApplicationTypeDTO(ateCode);
        this.applicationSubtype = new ApplicationSubtypeDTO(aseCode);
        this.recognitionCategory = new ReferenceDataDTO(ReferenceDataDomain.RECOGNITION_CATEGORY.domain(), rcyCode);
    }
}
