package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionMemberEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionMemberFilterDTO;

import java.util.List;

public interface CommissionMemberRepositoryCustom {

    List<CommissionMemberEntity> searchRecords(CommissionMemberFilterDTO filter);

    int getRecordsCount(CommissionMemberFilterDTO filter);
}
