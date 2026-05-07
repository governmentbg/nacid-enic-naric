package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDTO {
    private Integer id;
    private CountryDTO country;
    private String bgName;
    private String orgName;
    private AddressDTO address;
    private String webSite;
    private Boolean isActive;
    private String urlDiplomaRegister;
    private List<FacultyDTO> faculties;

    public UniversityDTO(Integer id) {
        this.id = id;
    }
}
