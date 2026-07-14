package bg.duosoft.nacid.backoffice.rudi.be.domain.factory;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.main.DocrecMainDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.main.SarMainDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.main.UdirecMainDataDTO;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiMainDataObjectFactory {

    private final JsonUtil jsonUtil;

    public RudiMainDataBaseDTO createObject(RudiApplicationDTO app, String json) {
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(app.getApplication().getApplicationType().getId(), app.getApplication().getApplicationSubtype().getId());
        switch (type) {
            case RUDI_SAR -> {
                return jsonUtil.readJson(json, SarMainDataDTO.class);
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                return jsonUtil.readJson(json, UdirecMainDataDTO.class);
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                return jsonUtil.readJson(json, DocrecMainDataDTO.class);
            }
            default -> throw new RuntimeException("[RudiMainDataDtoFactory] Cannot find main data dto for type " + type.name());
        }
    }
}
