package bg.duosoft.nacid.backoffice.rudi.be.mapper.provider;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec.DocrecStatusDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar.SarStatusDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec.UdirecStatusDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiStatusDataMapperProvider {
    private final SarStatusDataMapper sarMainDataMapper;
    private final UdirecStatusDataMapper udirecStatusDataMapper;
    private final DocrecStatusDataMapper docrecStatusDataMapper;

    public StatusDataMapperBase getMapper(RudiApplicationDTO app) {
        String applicationType = app.getApplication().getApplicationType().getId();
        String applicationSubType = app.getApplication().getApplicationSubtype().getId();
        return getMapper(applicationType, applicationSubType);
    }

    public StatusDataMapperBase getMapper(String applicationType, String applicationSubType) {
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(applicationType, applicationSubType);
        switch (type) {
            case RUDI_SAR -> {
                return sarMainDataMapper;
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                return udirecStatusDataMapper;
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                return docrecStatusDataMapper;
            }
            default -> throw new RuntimeException("Cannot find status data mapper for type " + type);
        }
    }
}
