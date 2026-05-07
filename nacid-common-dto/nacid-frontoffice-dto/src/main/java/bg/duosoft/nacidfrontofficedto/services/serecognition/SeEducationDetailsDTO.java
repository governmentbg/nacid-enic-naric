package bg.duosoft.nacidfrontofficedto.services.serecognition;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:16
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeEducationDetailsDTO {
    private Boolean hasCertificate;
    private Boolean hasOfficialNote;
    private Boolean hasVerificationLetter;
    private String schoolName;
    private String schoolSettlement;
    private Boolean isForeignGradingScale;
    private CountryDTO schoolCountry;
    private CountryDTO schoolGradingScaleCountry;
    private String schoolGradingScaleSettlement;
    private String schoolGradingScaleName;
    private ReferenceDataDTO internationalGradingSystem;
    private String diplomaNumber;
    private Integer diplomaYear;
    private LocalDate diplomaDate;
    private String diplomaRegisterURL;
    private String diplomaApostilleURL;
    private String professionBgTitle;
    private String professionNativeTitle;
    private List<SecEduStudiedSubjectDTO> studiedSubjects;
    private SecEduAdditionalSubjectsDTO additionalSubjects;
    private List<ReferenceDataDTO> recognitionPurposes;
    private String recognitionPurposeOtherDetails;
    private GradingScaleDTO gradingScale;
    private String additionalInfo;
    private String prevCertNumber;
    private String note;
}
