package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.DocumentTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DocumentTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, DocumentTypeEntity, DocumentTypeFilterDTO> implements DocumentTypeSearchRepository {

    @Override
    protected Class<DocumentTypeEntity> getEntityClass() {
        return DocumentTypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(DocumentTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String categoryCode = filter.getCategoryCode();
        if (StringUtils.hasText(categoryCode)) {
//            queryBuilder.append(" AND (SELECT count(e) from CfgDocTypeToDocCategoryEntity e where e.documentType.id = r.id and e.documentCategory.pk.id = :categoryCode) > 1 ");
            queryBuilder.append(" AND :categoryCode in (select x.documentCategory.pk.id from r.details x)");
            queryParameters.put("categoryCode", categoryCode);
        }

        String directionCode = filter.getDirectionCode();
        if (StringUtils.hasText(directionCode)){
            queryBuilder.append(" AND r.direction = :directionCode ");
            queryParameters.put("directionCode", directionCode);
        }

        String applicationType = filter.getApplicationType();
        if (StringUtils.hasText(applicationType)) {
            queryBuilder.append(" AND :applicationType in (select x.applicationType.id from r.details x)");
            queryParameters.put("applicationType", applicationType);
        }

        String applicationSubType = filter.getApplicationSubType();
        if (StringUtils.hasText(applicationSubType)) {
            queryBuilder.append(" AND :applicationSubType in (select x.applicationSubtype.id from r.details x)");
            queryParameters.put("applicationSubType", applicationSubType);
        }
    }
}
