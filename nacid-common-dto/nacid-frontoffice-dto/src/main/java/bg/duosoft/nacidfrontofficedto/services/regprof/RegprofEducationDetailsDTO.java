package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithServiceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:51
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofEducationDetailsDTO implements WithServiceType {

    private CountryDTO country;
    private boolean educationSelected;
    private boolean experienceSelected;
    private RegprofEducationDTO education;
    private RegprofExperienceDTO experience;
    private boolean nonRevokedRightToPractice;
    private String professionalQualificationRequested;
    private ReferenceDataDTO serviceType;
}
