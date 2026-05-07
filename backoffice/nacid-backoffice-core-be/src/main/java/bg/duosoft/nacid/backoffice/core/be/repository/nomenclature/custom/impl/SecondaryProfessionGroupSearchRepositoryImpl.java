package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SecondaryProfessionGroupSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondaryProfessionGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionGroupFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SecondaryProfessionGroupSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, SecondaryProfessionGroupEntity, SecondaryProfessionGroupFilterDTO> implements SecondaryProfessionGroupSearchRepository {
    @Override
    protected Class<SecondaryProfessionGroupEntity> getEntityClass() {
        return SecondaryProfessionGroupEntity.class;
    }

    @Override
    protected void additionalSearchQuery(SecondaryProfessionGroupFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String code = filter.getCode();
        if (StringUtils.hasText(code)) {
            queryBuilder.append(" AND LOWER(r.code) like LOWER(:code) ");
            queryParameters.put("code", "%" + code + "%");
        }
    }
}
