package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SarFlagDTO {

    private Boolean statuteFlag;
    private Boolean authenticityFlag;
    private Boolean recommendationFlag;

}
