package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsDeleteDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsDeleteDocServiceImpl implements AbdocsDeleteDocService {

    private final AbdocsAdminService abdocsAdminService;

    @Override
    public void deleteAbdocsDocOnException(Exception e, Doc document) {
        log.error(e.getMessage(), e);

        Integer docId = document.getDocId();
        try {
            abdocsAdminService.deleteDocument(docId);
        } catch (Exception ex) {
            log.error("[Abdocs File Transfer]  Cannot delete document with id " + docId);
            log.error(e.getMessage(), e);
        }
    }
}
