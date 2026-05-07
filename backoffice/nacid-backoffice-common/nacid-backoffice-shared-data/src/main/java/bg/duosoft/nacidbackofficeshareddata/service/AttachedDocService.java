package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationIdAndStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

import java.util.List;

public interface AttachedDocService {

    void updateDocType(Integer attachedDocId,Integer docTypeId);

    AttachedDocDTO selectById(Integer id);

    List<AttachedDocDTO> selectAllByApplicationId(Integer id);

    AttachedDocDTO selectByIdAndApplicationId(Integer id, Integer applicationId);

    void delete(Integer id);

    ApplicationIdAndStatusDTO selectApplicationIdAndStatusByAttachmentId(Integer attachmentId);
}
