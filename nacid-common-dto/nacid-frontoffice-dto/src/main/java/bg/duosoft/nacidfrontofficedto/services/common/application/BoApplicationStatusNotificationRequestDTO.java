package bg.duosoft.nacidfrontofficedto.services.common.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 31.01.2023
 * Time: 14:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoApplicationStatusNotificationRequestDTO {

    private Integer foApplicationId;


}
