package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.ProfGroupSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfGroupEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfGroupFilterDTO;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 15:04
 */
public class ProfGroupSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, ProfGroupEntity, ProfGroupFilterDTO> implements ProfGroupSearchRepository {

    @Override
    protected Class<ProfGroupEntity> getEntityClass() {
        return ProfGroupEntity.class;
    }

    @Override
    protected void additionalSearchQuery(ProfGroupFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        if(StringUtils.hasText(filter.getEducationAreaCode())) {
            queryBuilder.append(" AND r.educationArea.pk.id = :educationAreaCode");
            queryParameters.put("educationAreaCode", filter.getEducationAreaCode());
        }
    }
}