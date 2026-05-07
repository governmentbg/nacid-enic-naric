package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberDTO;
import java.util.List;

public interface ApplicationCommissionMemberService {
    List<ApplicationCommissionMemberDTO> selectByApplicationId(Integer applicationId);

    ApplicationCommissionMemberDTO selectById(Integer id);

    void delete(Integer id);
}
