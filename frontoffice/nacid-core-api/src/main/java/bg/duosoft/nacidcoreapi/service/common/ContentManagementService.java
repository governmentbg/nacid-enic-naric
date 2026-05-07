package bg.duosoft.nacidcoreapi.service.common;

import bg.duosoft.nacidfrontofficedto.contentmgmt.ContentManagementDTO;

import java.util.List;

public interface ContentManagementService {
    String findDataByIdAndActive(String id);

    ContentManagementDTO update(String data, String id);

    List<ContentManagementDTO> findByTypeAndActive(String type);
}
