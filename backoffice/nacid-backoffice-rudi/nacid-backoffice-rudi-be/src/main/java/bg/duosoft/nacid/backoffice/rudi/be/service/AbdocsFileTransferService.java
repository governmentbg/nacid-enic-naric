package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AttachmentType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberStatementDTO;

public interface AbdocsFileTransferService {

    ApplicationCommissionMemberStatementDTO transferStatementAttachment(Integer applicationId, Integer statementId);

}
