package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.DocrecRasInfoDTO;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 09.06.2023
 * Time: 10:11
 */
public interface RasService {
    boolean isApplicationTransferredInRas(Integer applicationId);

    void registerRasApplication(Integer applicationId, Integer certificateFileId);

    DocrecRasInfoDTO selectRasApplicationInfo(Integer applicationId);

    List<DocFile> selectCertificateFiles(Integer applicationId);

}
