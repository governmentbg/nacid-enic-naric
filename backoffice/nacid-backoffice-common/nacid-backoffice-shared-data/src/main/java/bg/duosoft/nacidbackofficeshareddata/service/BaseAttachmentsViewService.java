package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentsViewDTO;

import java.util.List;

public interface BaseAttachmentsViewService {

    AttachmentsViewDTO selectByAttachmentId(Integer attachmentId);

    List<AttachmentsViewDTO> selectTransferredAttachmentsByApplicationId(Integer applicationId);

}
