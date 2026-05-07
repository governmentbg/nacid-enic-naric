package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarGlobalReportDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;

import java.util.Map;

public interface ApplicationAttachmentService {
    AttachedDocDTO saveAttachment(Integer applicationId, AttachedDocDTO attachment);

    void delete(Integer id);

    Map<String, FileStoreEntryBaseDTO> generateGlobalReport(CommissionCalendarGlobalReportDTO globalReportDTO);
}
