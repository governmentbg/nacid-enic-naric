package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CommissionMemberPositionSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CommissionMemberPositionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CommissionMemberPositionFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommissionMemberPositionSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, CommissionMemberPositionEntity, CommissionMemberPositionFilterDTO> implements CommissionMemberPositionSearchRepository {

    @Override
    protected Class<CommissionMemberPositionEntity> getEntityClass() {
        return CommissionMemberPositionEntity.class;
    }

    @Override
    protected void additionalSearchQuery(CommissionMemberPositionFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String applicationStatus = filter.getApplicationStatus();
        if (StringUtils.hasText(applicationStatus)) {
            queryBuilder.append(" AND r.applicationStatus.pk.id = :applicationStatus and r.applicationStatus.pk.domain = '" + ReferenceDataDomain.APPLICATION_STATUS.domain() + "' ");
            queryParameters.put("applicationStatus", applicationStatus);
        }
    }

}
