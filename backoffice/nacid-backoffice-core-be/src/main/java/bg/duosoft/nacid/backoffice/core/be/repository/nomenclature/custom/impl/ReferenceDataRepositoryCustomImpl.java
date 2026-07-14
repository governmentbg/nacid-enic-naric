package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ReferenceDataRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ReferenceDataFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import bg.duosoft.nacidbackofficeshareddata.utils.NomenclatureSearchQueryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReferenceDataRepositoryCustomImpl extends BaseRepositoryCustomImpl implements ReferenceDataRepositoryCustom {

    @Override
    public List<ReferenceDataEntity> selectReferenceData(ReferenceDataFilterDTO filter) {
        TypedQuery<ReferenceDataEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int selectReferenceDataCount(ReferenceDataFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(ReferenceDataFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(DISTINCT(r)) " : " DISTINCT(r) ");
        queryBuilder.append(" FROM ").append(ReferenceDataEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        String domain = filter.getDomain();
        if (StringUtils.hasText(domain)) {
            queryBuilder.append(" AND LOWER(r.pk.domain) like LOWER(:domain) ");
            queryParameters.put("domain", "%" + domain + "%");
        }

        String id = filter.getId();
        if (StringUtils.hasText(id)) {
            queryBuilder.append(" AND LOWER(r.pk.id) like LOWER(:id) ");
            queryParameters.put("id", "%" + id + "%");
        }

        Integer index = filter.getIndex();
        if (Objects.nonNull(index)) {
            queryBuilder.append(" AND r.index = :index ");
            queryParameters.put("index", index);
        }

        NomenclatureSearchQueryUtils.appendNameSearchQuery(filter, queryBuilder, queryParameters);
        NomenclatureSearchQueryUtils.appendActiveSearchQuery(filter, queryBuilder, queryParameters);
        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : ReferenceDataEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
