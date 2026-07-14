package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.GraduationDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.SpecialityDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithSpecialities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:56
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofEducationEntryDTO implements WithSpecialities {

    private String oldEducationInstitutionName;
    private String oldEducationInstitutionId;
    private String newEducationInstitutionName;
    private String newEducationInstitutionId;
    private String professionalQualification;
    private String professionalQualificationId;
    private List<SpecialityDTO> specialities;
    private SpecialityDTO specialitySingle;
    private GraduationDocTypeDTO documentKind;
    private String documentSeries;
    private String documentNumber;
    private String documentRegistrationNumber;
    private LocalDate documentDate;
    private ReferenceDataDTO qualificationRank;
    private ReferenceDataDTO eduLevel;
}
