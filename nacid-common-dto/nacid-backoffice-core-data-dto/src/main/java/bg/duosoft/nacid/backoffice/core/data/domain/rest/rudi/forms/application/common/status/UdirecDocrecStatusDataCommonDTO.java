package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdirecDocrecStatusDataCommonDTO extends RudiStatusDataBaseDTO {
    private StringIdDTO recognizedEduLevel;
    private Integer recognizedProfGroupId;
}
