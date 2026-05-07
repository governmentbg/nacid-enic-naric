package bg.duosoft.nacidfrontofficedto.services.unichecks;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:45
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UniChecksEducationDetailsDTO extends RudiEducationDetailsDTO implements WithSpecialities, WithGainedQualification, WithServiceType, WithRecognitionCategory {

    private List<SpecialityDTO> specialities;
    private SpecialityDTO specialitySingle;
    private String gainedQualification;
    private String originalGainedQualification;
    private String nacidOutgoingNumber;
    private String applicantIncomingNumber;
    private NaturalPersonDTO diplomaHolder;
    private String diplomaHolderEan;

    private Boolean statute;
    private Boolean authenticity;
    private Boolean recommendation;

    private ReferenceDataDTO serviceType;

    private ReferenceDataDTO recognitionCategory;
}
