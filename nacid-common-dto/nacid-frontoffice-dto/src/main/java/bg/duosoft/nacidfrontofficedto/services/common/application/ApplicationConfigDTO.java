package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 16:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApplicationConfigDTO {

    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
}
