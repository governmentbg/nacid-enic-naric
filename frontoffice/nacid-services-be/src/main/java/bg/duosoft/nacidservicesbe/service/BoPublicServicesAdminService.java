package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCertificateDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsApplicationInsertRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsDocDetailsDTO;
import org.springframework.http.ResponseEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 16:14
 */
public interface BoPublicServicesAdminService {

    void insertApplicationToDms(DmsApplicationInsertRequestDTO insertRequest);
    ApplicationCertificateDTO getCertificateForApplication(Integer applicationId);
    DmsDocDetailsDTO getDmsDocDetailsForBoAttachedDocId(Integer boAttachedDocId);
    ResponseEntity<byte[]> downloadDmsFile(Integer docId, Integer fileId);
}
