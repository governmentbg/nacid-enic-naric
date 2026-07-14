package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.DocTypeSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocTypeFilterDTO;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 18:25
 */
public class DocTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, DocTypeEntity, DocTypeFilterDTO> implements DocTypeSearchRepository {

    @Override
    protected Class<DocTypeEntity> getEntityClass() {
        return DocTypeEntity.class;
    }

    @Override
    protected void additionalSearchQuery(DocTypeFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        if(filter.getApplicationType() != null || filter.getApplicationSubtype() != null) {
            queryBuilder.insert(queryBuilder.indexOf("WHERE"), " JOIN r.configs c ");

            if(filter.getApplicationType() != null){
                queryBuilder.append(" AND c.applicationTypeCode = :typeCode");
                queryParameters.put("typeCode", filter.getApplicationType().getCode());
            }

            if(filter.getApplicationSubtype() != null) {
                queryBuilder.append(" AND c.applicationSubtypeCode = :subtypeCode");
                queryParameters.put("subtypeCode", filter.getApplicationSubtype().getCode());
            }
        }
    }
}
