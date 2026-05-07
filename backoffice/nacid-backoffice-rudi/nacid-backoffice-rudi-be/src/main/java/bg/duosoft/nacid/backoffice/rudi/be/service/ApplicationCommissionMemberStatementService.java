package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberStatementDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;

import java.util.List;

public interface ApplicationCommissionMemberStatementService {

    List<ApplicationCommissionMemberStatementDTO> selectByApplicationId(Integer applicationId);

    ApplicationCommissionMemberStatementDTO selectById(Integer id);

    void delete(Integer id);

    RudiApplicationDTO saveApplicationCommissionMemberData(Integer applicationId, ApplicationCommissionMemberStatementDTO requestData);
}
