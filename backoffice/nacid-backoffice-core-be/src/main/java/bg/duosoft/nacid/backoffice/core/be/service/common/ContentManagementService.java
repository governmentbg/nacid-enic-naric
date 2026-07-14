package bg.duosoft.nacid.backoffice.core.be.service.common;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ContentManagementDTO;

import java.util.List;

public interface ContentManagementService {
    String findDataByIdAndActive(String id);

    ContentManagementDTO update(String data, String id);

    List<ContentManagementDTO> findByTypeAndActive(String type);
}
