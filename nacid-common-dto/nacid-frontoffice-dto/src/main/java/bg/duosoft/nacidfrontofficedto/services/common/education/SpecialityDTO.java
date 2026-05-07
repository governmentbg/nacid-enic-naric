package bg.duosoft.nacidfrontofficedto.services.common.education;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.03.2023
 * Time: 15:22
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityDTO {

    private Integer id;
    private String name;
    private String originalName;
}
