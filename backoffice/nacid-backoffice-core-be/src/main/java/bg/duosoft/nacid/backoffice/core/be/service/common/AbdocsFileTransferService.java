package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

public interface AbdocsFileTransferService {

    AttachedDocDTO transferApplicationAttachment(Integer applicationId, Integer attachedDocId);

}
