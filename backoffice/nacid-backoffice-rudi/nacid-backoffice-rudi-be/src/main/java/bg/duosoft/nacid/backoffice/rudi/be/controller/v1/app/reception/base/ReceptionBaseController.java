package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.reception.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception.RudiBaseReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.reception.DocrecReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.reception.SarReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.reception.UdirecReceptionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec.DocrecReceptionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar.SarReceptionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec.UdirecReceptionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReceptionService;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_CREATE;

@Slf4j
public abstract class ReceptionBaseController extends BaseAccessController {

    @Autowired
    protected ReceptionService rudiReceptionService;

    @Autowired
    protected DocrecReceptionMapper docrecReceptionMapper;

    @Autowired
    protected SarReceptionMapper sarReceptionMapper;

    @Autowired
    protected UdirecReceptionMapper udirecReceptionMapper;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_CREATE;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_CREATE;
    }

    protected IntegerIdDTO processReception(RudiBaseReceptionDTO requestData) {
        RudiApplicationDTO application = new RudiApplicationDTO();

        if (requestData instanceof DocrecReceptionDTO receptionDTO) {
            docrecReceptionMapper.overrideApplicationData(receptionDTO, application);
        } else if (requestData instanceof SarReceptionDTO receptionDTO) {
            sarReceptionMapper.overrideApplicationData(receptionDTO, application);
        } else if (requestData instanceof UdirecReceptionDTO receptionDTO) {
            udirecReceptionMapper.overrideApplicationData(receptionDTO, application);
        }

        RudiApplicationDTO reception = rudiReceptionService.createReception(application);
        return new IntegerIdDTO(reception.getApplication().getId());
    }

}
