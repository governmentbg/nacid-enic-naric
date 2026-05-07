package bg.duosoft.nacidservicesbe.controller.utils;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.RudiEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithSpecialities;
import bg.duosoft.nacidshareddata.util.ReferenceDataConstants;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.11.2022
 * Time: 17:25
 */
public class EducationDetailsDTOUtils {

    public static void preSaveEducationDetailsSpecialities(WithSpecialities withSpecialities){
        if(withSpecialities.getSpecialities() == null){
            withSpecialities.setSpecialities(new ArrayList<>());
        }
        if(withSpecialities.getSpecialitySingle() != null && StringUtils.hasText(withSpecialities.getSpecialitySingle().getName())){
            withSpecialities.getSpecialities().add(withSpecialities.getSpecialitySingle());
            withSpecialities.setSpecialitySingle(null);
        }
    }

    public static void preSaveEducationDetails(RudiEducationDetailsDTO rudiEducationDetails){
        if(rudiEducationDetails.getEducationDurationType() != null){
            if(!StringUtils.hasText(rudiEducationDetails.getEducationDurationType().getId())){
                rudiEducationDetails.setEducationDurationType(null);
            }
        }
        if(rudiEducationDetails.getEducationForm() != null){
            if(!StringUtils.hasText(rudiEducationDetails.getEducationForm().getId())){
                rudiEducationDetails.setEducationForm(null);
            } else if(!ReferenceDataConstants.OTHER_VALUE.equals(rudiEducationDetails.getEducationForm().getId())){
                rudiEducationDetails.setEducationFormOtherDetails(null);
            }
        }
        if(rudiEducationDetails.getGraduationWay() != null){
            ReferenceDataDTO other = rudiEducationDetails.getGraduationWay().stream().filter(gw -> ReferenceDataConstants.OTHER_VALUE.equals(gw.getId())).findFirst().orElse(null);
            if(other == null){
                rudiEducationDetails.setGraduationWayOtherDetails(null);
            }
            rudiEducationDetails.setGraduationWay(rudiEducationDetails.getGraduationWay().stream().filter(gw-> StringUtils.hasText(gw.getId())).collect(Collectors.toList()));
        }
    }
}
