package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CommissionMemberPositionSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CommissionMemberPositionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CommissionMemberPositionFilterDTO;

public interface CommissionMemberPositionRepository extends NomenclatureBaseRepository<String, CommissionMemberPositionEntity, CommissionMemberPositionFilterDTO>, CommissionMemberPositionSearchRepository {

}
