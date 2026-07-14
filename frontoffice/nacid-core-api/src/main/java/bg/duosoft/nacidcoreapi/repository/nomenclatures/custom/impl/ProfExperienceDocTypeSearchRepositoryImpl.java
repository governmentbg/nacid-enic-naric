package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.ProfExperienceDocTypeSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfExperienceDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfExperienceDocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 10:38
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ProfExperienceDocTypeSearchRepositoryImpl  extends NomenclatureSearchRepositoryImpl<String, ProfExperienceDocTypeEntity, ProfExperienceDocTypeFilterDTO> implements ProfExperienceDocTypeSearchRepository {

    @Override
    protected Class<ProfExperienceDocTypeEntity> getEntityClass() {
        return ProfExperienceDocTypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(ProfExperienceDocTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        Boolean forExperienceCalculation = filter.getForExperienceCalculation();
        if (Objects.nonNull(forExperienceCalculation)) {
            queryBuilder.append(" AND r.forExperienceCalculation = :forExperienceCalculation ");
            queryParameters.put("forExperienceCalculation", forExperienceCalculation != null && forExperienceCalculation ? 1 : 0);
        }
    }
}
