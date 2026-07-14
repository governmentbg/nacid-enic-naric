package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;

public interface AbdocsCoreService {

    Doc selectAbdocsDoc(Integer docflowId);
    DocFile selecAbdocsDocFile(Integer docflowId, Integer fileId);
    DownloadFileResponse getAbdocsDownloadFile(DocFile docFile);

}
