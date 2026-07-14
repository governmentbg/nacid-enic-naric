package bg.duosoft.nacid.backoffice.rudi.be.domain.factory;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.status.DocrecStatusDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.status.SarStatusDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.status.UdirecStatusDataDTO;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiStatusDataObjectFactory {
    private final JsonUtil jsonUtil;

    public RudiStatusDataBaseDTO createObject(RudiApplicationDTO app, String json) {
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(app.getApplication().getApplicationType().getId(), app.getApplication().getApplicationSubtype().getId());
        switch (type) {
            case RUDI_SAR -> {
                return jsonUtil.readJson(json, SarStatusDataDTO.class);
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                return jsonUtil.readJson(json, UdirecStatusDataDTO.class);
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                return jsonUtil.readJson(json, DocrecStatusDataDTO.class);
            }
            default -> throw new RuntimeException("[RudiStatusDataDtoFactory] Cannot find status data dto for type " + type.name());
        }
    }
}
