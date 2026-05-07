package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;

public interface AbdocsDeleteDocService {
    void deleteAbdocsDocOnException(Exception e, Doc document);
}
