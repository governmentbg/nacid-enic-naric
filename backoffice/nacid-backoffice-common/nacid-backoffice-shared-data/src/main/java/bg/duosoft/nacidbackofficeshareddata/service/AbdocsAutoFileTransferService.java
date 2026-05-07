package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

import java.util.List;

public interface AbdocsAutoFileTransferService {

    boolean transferFiles(Integer applicationId, List<AttachedDocDTO> attachedDocs);

    boolean transferFile(Integer applicationId, AttachedDocDTO attachedDocs);

    boolean transferApplicationFiles(Integer applicationId);

}
