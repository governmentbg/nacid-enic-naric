package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 05.09.2022
 * Time: 13:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgGraduationDocumentTypeConfigDTO {
    private CountryDTO country;
    private ReferenceDataDTO educationType;
}
