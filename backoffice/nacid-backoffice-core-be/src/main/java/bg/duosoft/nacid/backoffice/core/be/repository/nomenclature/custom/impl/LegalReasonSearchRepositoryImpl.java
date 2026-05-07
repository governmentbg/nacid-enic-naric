package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.LegalReasonSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LegalReasonFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LegalReasonSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, LegalReasonEntity, LegalReasonFilterDTO> implements LegalReasonSearchRepository {

    @Override
    protected Class<LegalReasonEntity> getEntityClass() {
        return LegalReasonEntity.class;
    }

    @Override
    protected void additionalJoinQuery(LegalReasonFilterDTO filter, StringBuilder queryBuilder) {
        queryBuilder.append(" LEFT JOIN r.configs c ");
    }

    @Override
    protected void additionalSearchQuery(LegalReasonFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String applicationStatus = filter.getApplicationStatus();
        if (StringUtils.hasText(applicationStatus)) {
            queryBuilder.append(" AND r.applicationStatus.pk.id = :applicationStatus and r.applicationStatus.pk.domain = '" + ReferenceDataDomain.APPLICATION_STATUS.domain() + "' ");
            queryParameters.put("applicationStatus", applicationStatus);
        }

        String ordinanceArticle = filter.getOrdinanceArticle();
        if (StringUtils.hasText(ordinanceArticle)) {
            queryBuilder.append(" AND LOWER(r.ordinanceArticle) like LOWER(:ordinanceArticle) ");
            queryParameters.put("ordinanceArticle", "%" + ordinanceArticle + "%");
        }

        String regulationArticle = filter.getRegulationArticle();
        if (StringUtils.hasText(regulationArticle)) {
            queryBuilder.append(" AND LOWER(r.regulationArticle) like LOWER(:regulationArticle) ");
            queryParameters.put("regulationArticle", "%" + regulationArticle + "%");
        }

        String regulationText = filter.getRegulationText();
        if (StringUtils.hasText(regulationText)) {
            queryBuilder.append(" AND LOWER(r.regulationText) like LOWER(:regulationText) ");
            queryParameters.put("regulationText", "%" + regulationText + "%");
        }

        String applicationType = filter.getApplicationType();
        if (StringUtils.hasText(applicationType)) {
            queryBuilder.append(" AND c.applicationType.id = :applicationType ");
            queryParameters.put("applicationType", applicationType);
        }

        String applicationSubType = filter.getApplicationSubType();
        if (StringUtils.hasText(applicationSubType)) {
            queryBuilder.append(" AND c.applicationSubtype.id = :applicationSubType ");
            queryParameters.put("applicationSubType", applicationSubType);
        }
    }

}
