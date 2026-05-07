package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;


import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SecondarySpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondarySpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondarySpecialityFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SecondarySpecialitySearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, SecondarySpecialityEntity, SecondarySpecialityFilterDTO> implements SecondarySpecialitySearchRepository {

    @Override
    protected Class<SecondarySpecialityEntity> getEntityClass() {
        return SecondarySpecialityEntity.class;
    }

    @Override
    protected void additionalSearchQuery(SecondarySpecialityFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String code = filter.getCode();
        if (StringUtils.hasText(code)) {
            queryBuilder.append(" AND LOWER(r.code) like LOWER(:code) ");
            queryParameters.put("code", "%" + code + "%");
        }

        Integer qualificationCode = filter.getQualificationCode();
        if (Objects.nonNull(qualificationCode)) {
            queryBuilder.append(" AND r.qualification.id =:qualificationCode ");
            queryParameters.put("qualificationCode", qualificationCode);
        }

        String degreeCode = filter.getQualificationDegreeCode();
        if (StringUtils.hasText(degreeCode)) {
            queryBuilder.append(" AND r.qualificationDegree.pk.id =:degreeCode ");
            queryParameters.put("degreeCode", degreeCode);
        }
    }
}
