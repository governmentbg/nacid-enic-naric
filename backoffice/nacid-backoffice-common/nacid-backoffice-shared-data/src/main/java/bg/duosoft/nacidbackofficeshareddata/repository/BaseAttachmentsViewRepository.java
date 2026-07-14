package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.VAttachmentsEntity;

import java.util.List;

public interface BaseAttachmentsViewRepository {

    VAttachmentsEntity selectByAttachmentId(Integer attachmentId);

    List<VAttachmentsEntity> selectTransferredAttachmentsByApplicationId(Integer applicationId);

}
