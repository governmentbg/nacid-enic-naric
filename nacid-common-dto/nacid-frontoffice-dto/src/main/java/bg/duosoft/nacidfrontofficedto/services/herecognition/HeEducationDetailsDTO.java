package bg.duosoft.nacidfrontofficedto.services.herecognition;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.*;
import bg.duosoft.nacidfrontofficedto.services.wrapper.RecognitionAimWrapperDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:16
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeEducationDetailsDTO extends RudiEducationDetailsDTO
        implements WithPreviousUniversityDiploma, WithSpecialities, WithGainedQualification {

    private List<SpecialityDTO> specialities;
    private SpecialityDTO specialitySingle;
    private String gainedQualification;
    private String originalGainedQualification;
    private HighSchoolDiplomaDTO highSchoolDiploma;
    private List<ReferenceDataDTO> recognitionAim;
    private String recognitionAimOtherDetails;
    private PreviousUniversityDiplomaDTO previousUniversityDiploma;

    public RecognitionAimWrapperDTO getRecognitionAimWrapper(){
        return new RecognitionAimWrapperDTO(recognitionAim, recognitionAimOtherDetails);
    }

    public void setRecognitionAimWrapper(RecognitionAimWrapperDTO wrapper){
        if(wrapper != null){
            setRecognitionAim(wrapper.getRecognitionAim());
            setRecognitionAimOtherDetails(wrapper.getRecognitionAimOtherDetails());
        }
    }
}
