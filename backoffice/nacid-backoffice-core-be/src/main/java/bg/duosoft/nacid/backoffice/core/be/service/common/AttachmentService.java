package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;

public interface AttachmentService {

    AttachmentDTO selectById(Integer id);

    AttachmentDTO save(AttachmentDTO attachmentDTO);

}
