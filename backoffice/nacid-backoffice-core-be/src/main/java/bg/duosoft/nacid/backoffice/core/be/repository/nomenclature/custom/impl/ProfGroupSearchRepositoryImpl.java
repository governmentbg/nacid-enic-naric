package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ProfGroupSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfGroupFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProfGroupSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, ProfGroupEntity, ProfGroupFilterDTO> implements ProfGroupSearchRepository {

    @Override
    protected Class<ProfGroupEntity> getEntityClass() {
        return ProfGroupEntity.class;
    }

    @Override
    protected void additionalSearchQuery(ProfGroupFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String educationArea = filter.getEducationArea();
        if (StringUtils.hasText(educationArea)) {
            queryBuilder.append(" AND r.educationArea.pk.id = :educationArea and r.educationArea.pk.domain = '" + ReferenceDataDomain.EDUCATION_AREA.domain() + "' ");
            queryParameters.put("educationArea", educationArea);
        }

    }

}
