package bg.duosoft.nacidfrontofficedto.services.wrapper;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 17:38
 */
@Data
@AllArgsConstructor
@JsonIgnoreType
public class EducationFormWrapperDTO {

    private ReferenceDataDTO educationForm;
    private String  educationFormOtherDetails;
}
