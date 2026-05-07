package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.payments.dto.payments.LiabilityDetailDTO;

public interface ApplicationPaymentsService {
    void save(Integer applicationId, LiabilityDetailDTO liabilityDetail);
    void delete(Integer liabilityId, Integer liabilityDetailId);
    public void insertFees(Integer applicationId);
}
