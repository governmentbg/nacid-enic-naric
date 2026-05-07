package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.UpdateAttachmentsResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

public interface BaseApplicationAttachmentService {
    UpdateAttachmentsResultDTO attachmentUpdates(ApplicationDTO application, AttachedDocDTO attachment);

    void addNewCertificateOnCreate(ApplicationDTO savedApplication, UpdateAttachmentsResultDTO updateAttachmentsResultDTO);

    AttachedDocDTO selectAttachment(Integer id);
}
