package bg.duosoft.nacid.backoffice.core.be.service.common.accept_app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

import java.util.List;

public interface AcceptApplicationFileService {

    List<AttachedDocDTO> processFiles(ApplicationDTO application);

    AttachmentDTO processDocDeliveryAttachment(String foFileNameAndId, ApplicationDTO application);

}
