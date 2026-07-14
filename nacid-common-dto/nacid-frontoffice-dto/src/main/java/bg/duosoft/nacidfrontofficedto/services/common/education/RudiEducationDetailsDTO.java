package bg.duosoft.nacidfrontofficedto.services.common.education;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.wrapper.EducationFormWrapperDTO;
import bg.duosoft.nacidfrontofficedto.services.wrapper.GraduationWayWrapperDTO;
import bg.duosoft.nacidfrontofficedto.services.wrapper.RecognitionAimWrapperDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.07.2022
 * Time: 11:28
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RudiEducationDetailsDTO {
    private List<UniversityDataDTO> universitiesData;
    private DiplomaDTO diploma;
    private List<EducationPlaceDTO> educationPlaces;
    private String startOfEducation;
    private String endOfEducation;
    private String educationDuration;
    private ReferenceDataDTO educationDurationType;
    private ReferenceDataDTO educationForm;
    private String  educationFormOtherDetails;
    private List<ReferenceDataDTO> graduationWay;
    private String graduationWayOtherDetails;
    private String credits;

    private String originalGainedLevel;
    private String originalGainedLevelTranslated;

    public EducationFormWrapperDTO getEducationFormWrapper(){
        return new EducationFormWrapperDTO(educationForm, educationFormOtherDetails);
    }

    public void setEducationFormWrapper(EducationFormWrapperDTO wrapper){
        if(wrapper != null){
            setEducationForm(wrapper.getEducationForm());
            setEducationFormOtherDetails(wrapper.getEducationFormOtherDetails());
        }
    }

    public GraduationWayWrapperDTO getGraduationWayWrapper(){
        return new GraduationWayWrapperDTO(graduationWay, graduationWayOtherDetails);
    }

    public void setGraduationWayWrapper(GraduationWayWrapperDTO wrapper){
        if(wrapper != null){
            setGraduationWay(wrapper.getGraduationWay());
            setGraduationWayOtherDetails(wrapper.getGraduationWayOtherDetails());
        }
    }

    public boolean containsGraduationWayCode(String code){
        if(graduationWay != null && graduationWay.size() >0){
            boolean contained = graduationWay.stream().anyMatch(gw -> gw.getId().equals(code));
            return contained;
        }
        return false;
    }
}
