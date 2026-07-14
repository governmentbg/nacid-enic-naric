package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ProfessionExperienceDocumentTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfessionExperienceDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfessionExperienceDocumentTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProfessionExperienceDocumentTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, ProfessionExperienceDocumentTypeEntity, ProfessionExperienceDocumentTypeFilterDTO> implements ProfessionExperienceDocumentTypeSearchRepository {


    @Override
    protected Class<ProfessionExperienceDocumentTypeEntity> getEntityClass() {
        return ProfessionExperienceDocumentTypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(ProfessionExperienceDocumentTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        Boolean isForExperienceCalculation = filter.getIsForExperienceCalculation();
        
        if (Objects.nonNull(isForExperienceCalculation)) {
            queryBuilder.append(" AND r.forExperienceCalculationFlag = :forExperienceCalculationFlag ");
            queryParameters.put("forExperienceCalculationFlag", isForExperienceCalculation ? 1 : 0);
        }
    }
}
