package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.Direction;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 18.08.2022
 * Time: 15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeDTO extends IntegerKeyNomenclatureBase {
    public DocumentTypeDTO(Integer id) {
        this.id = id;
    }

    private String direction;
    private String validationFileGroup;
    private List<DocumentTypeDetailDTO> details;
    private List<DocumentTypeAppStatusDetailDTO> statuses;
    private List<DocumentTypeAbdocsConfigDTO> abdocsConfigs;

    public List<DocumentTypeAbdocsConfigDTO> getAbdocsConfigs(String applicationType, String applicationSubtype) {
        if (abdocsConfigs == null || abdocsConfigs.isEmpty()) {
            return new ArrayList<>();
        }
        return abdocsConfigs.stream().filter(f -> (f.getApplicationType() == null || Objects.equals(applicationType, f.getApplicationType().getId())) && (f.getApplicationSubtype() == null || Objects.equals(applicationSubtype, f.getApplicationSubtype().getId()))).toList();
    }
    public DocumentTypeAbdocsConfigDTO getSingleAbdocsConfigOrThrowException(String applicationType, String applicationSubtype) {
        List<DocumentTypeAbdocsConfigDTO> adc = getAbdocsConfigs(applicationType, applicationSubtype);
        if (adc.size() == 0) {
            if (Direction.selectByCode(direction) == Direction.Input) {
                return null;
            }
            throw new RuntimeException("Cannot find configuration inside nomenclatures.cfg_doc_type_to_abdocs_config for applicationType = %s and applicationSubtype = %s".formatted(applicationType, applicationSubtype));
        } else if (adc.size() > 1) {
            throw new RuntimeException("There is more than one configuration inside nomenclatures.cfg_doc_type_to_abdocs_config for applicationType = %s and applicationSubtype = %s. Configs:%s".formatted(applicationType, applicationSubtype, adc));
        }
        return adc.get(0);
    }
}
