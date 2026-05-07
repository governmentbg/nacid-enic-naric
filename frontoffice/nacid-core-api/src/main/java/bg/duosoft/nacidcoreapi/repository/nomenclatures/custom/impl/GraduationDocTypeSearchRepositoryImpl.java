package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.GraduationDocTypeSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:36
 */
public class GraduationDocTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, GraduationDocTypeEntity, GraduationDocTypeFilterDTO> implements GraduationDocTypeSearchRepository {

    @Override
    protected Class<GraduationDocTypeEntity> getEntityClass() {
        return GraduationDocTypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(GraduationDocTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        if(filter.getEducationType() != null) {
            queryBuilder.insert(queryBuilder.indexOf("WHERE"), " JOIN r.configs c ");
            queryBuilder.append(" AND c.id.educationType =: educationType");
            queryParameters.put("educationType", filter.getEducationType().getCode());
        }
    }
}
