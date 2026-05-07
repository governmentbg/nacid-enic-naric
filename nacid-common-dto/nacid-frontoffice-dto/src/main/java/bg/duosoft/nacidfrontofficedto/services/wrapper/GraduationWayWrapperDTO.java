package bg.duosoft.nacidfrontofficedto.services.wrapper;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 17:39
 */
@Data
@AllArgsConstructor
@JsonIgnoreType
public class GraduationWayWrapperDTO {

    private List<ReferenceDataDTO> graduationWay;
    private String graduationWayOtherDetails;
}
