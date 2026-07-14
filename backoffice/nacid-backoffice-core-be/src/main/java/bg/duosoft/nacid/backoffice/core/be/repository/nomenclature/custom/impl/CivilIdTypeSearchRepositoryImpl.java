package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CivilIdTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CivilIdTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CivilIdTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CivilIdTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, CivilIdTypeEntity, CivilIdTypeFilterDTO> implements CivilIdTypeSearchRepository {

    @Override
    protected Class<CivilIdTypeEntity> getEntityClass() {
        return CivilIdTypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(CivilIdTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String legalType = filter.getLegalType();
        if (StringUtils.hasText(legalType)) {
            queryBuilder.append(" AND r.legalType.pk.id = :legalType and r.legalType.pk.domain = '" + ReferenceDataDomain.LEGAL_TYPE.domain() + "' ");
            queryParameters.put("legalType", legalType);
        }
    }

}
