package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionMemberDTO {
    public CommissionMemberDTO(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String civilId;
    private CivilIdTypeDTO civilIdType;
    private String degree;
    private String institution;
    private String division;
    private String title;
    private ReferenceDataDTO commissionPosition;
    private ProfGroupDTO profGroup;
    private String iban;
    private String bic;
    private AddressDTO address;
    private Boolean isActive;
}
