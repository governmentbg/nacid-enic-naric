package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

public interface AbdocsUpdateAttachmentService {
    AttachedDocDTO updateDocflowId(Integer attachedDocId, String docflowId);
}
