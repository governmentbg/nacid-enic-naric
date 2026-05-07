package bg.duosoft.nacidfrontofficedto.services.common.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 15:17
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDTO {

    @NonNull
    private String evaluationCode;
    @NonNull
    private boolean evaluationValue;
    private String templateUrl;
}
