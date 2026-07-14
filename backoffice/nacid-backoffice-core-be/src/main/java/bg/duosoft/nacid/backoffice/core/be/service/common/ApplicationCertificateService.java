package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;

import java.util.List;

public interface ApplicationCertificateService {

    List<ApplicationCertificatesDTO> selectCertificatesForFO(Integer efilingId);

}
