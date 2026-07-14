package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecondarySpecialityDTO extends IntegerKeyNomenclatureBase {
    public SecondarySpecialityDTO(Integer id) {
        this.id = id;
    }

    private String code;
    private SecondaryProfessionalQualificationDTO qualification;
    private ReferenceDataDTO qualificationDegree;
}
