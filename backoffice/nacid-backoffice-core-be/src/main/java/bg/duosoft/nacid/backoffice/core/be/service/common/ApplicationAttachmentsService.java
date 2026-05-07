package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

import java.util.List;

public interface ApplicationAttachmentsService {
    List<AttachedDocDTO> selectApplicationAttachments(Integer applicationId, String direction, Boolean finalized);

    List<AttachedDocDTO> selectAttachmentsByDocCategory(Integer applicationId, String direction, String docCategory);

    AttachedDocDTO selectById(Integer id);

    AttachedDocDTO updateDocflowId(Integer attachedDocId, String docflowId);

}
