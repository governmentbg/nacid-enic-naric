package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ApplicationSubTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationSubTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ApplicationSubTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, ApplicationSubtypeEntity, ApplicationSubTypeFilterDTO> implements ApplicationSubTypeSearchRepository {

    @Override
    protected Class<ApplicationSubtypeEntity> getEntityClass() {
        return ApplicationSubtypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(ApplicationSubTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String applicationType = filter.getApplicationType();
        if (StringUtils.hasText(applicationType)) {
            queryBuilder.append(" AND r.applicationType.id = :applicationType  ");
            queryParameters.put("applicationType", applicationType);
        }
    }
}
