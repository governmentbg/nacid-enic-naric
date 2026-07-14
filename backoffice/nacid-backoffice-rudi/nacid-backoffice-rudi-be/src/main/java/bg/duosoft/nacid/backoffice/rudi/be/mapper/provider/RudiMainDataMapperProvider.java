package bg.duosoft.nacid.backoffice.rudi.be.mapper.provider;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main.MainDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec.DocrecMainDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar.SarMainDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec.UdirecMainDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiMainDataMapperProvider {

    private final SarMainDataMapper sarMainDataMapper;
    private final UdirecMainDataMapper udirecMainDataMapper;
    private final DocrecMainDataMapper docrecMainDataMapper;

    public MainDataMapperBase getMapper(RudiApplicationDTO app) {
        String applicationType = app.getApplication().getApplicationType().getId();
        String applicationSubType = app.getApplication().getApplicationSubtype().getId();
        return getMapper(applicationType, applicationSubType);
    }

    public MainDataMapperBase getMapper(String applicationType, String applicationSubType) {
        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(applicationType, applicationSubType);
        switch (type) {
            case RUDI_SAR -> {
                return sarMainDataMapper;
            }
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                return udirecMainDataMapper;
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                return docrecMainDataMapper;
            }
            default -> throw new RuntimeException("Cannot find main data mapper for type " + type);
        }
    }

}
