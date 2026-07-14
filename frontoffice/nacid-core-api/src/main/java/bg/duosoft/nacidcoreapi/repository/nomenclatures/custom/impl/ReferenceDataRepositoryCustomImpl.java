package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.ReferenceDataRepositoryCustom;
import bg.duosoft.nacidcoreapi.util.nomenclatures.NomenclatureSearchQueryUtils;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataDomainEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidcoreapi.repository.BaseRepositoryCustomImpl;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ReferenceDataFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
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
    public List<ReferenceDataEntity> selectFoReferenceData(ReferenceDataFilterDTO filter) {
        TypedQuery<ReferenceDataEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int selectFoReferenceDataCount(ReferenceDataFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(ReferenceDataFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(DISTINCT(r)) " : " DISTINCT(r) ");
        queryBuilder.append(" FROM ").append(ReferenceDataEntity.class.getSimpleName()).append(" r ")
                .append(" JOIN ").append(ReferenceDataDomainEntity.class.getSimpleName()).append(" d ON d.domain = r.pk.domain ");
        queryBuilder.append(" WHERE d.foOnly = 1 ");

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
