package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationIdAndStatusDTO;

import java.util.List;


public interface AttachedDocRepository {
    void updateDocType(Integer attachedDocId, Integer docTypeId);
    ApplicationAttachedDocEntity selectById(Integer id);

    List<ApplicationAttachedDocEntity> selectAllByApplicationId(Integer id);

    ApplicationAttachedDocEntity selectByIdAndApplicationId(Integer id, Integer applicationId);

    void delete(Integer id);
    ApplicationIdAndStatusDTO selectApplicationIdAndStatusByAttachmentId(Integer attachmentId);
}
