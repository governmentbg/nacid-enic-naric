package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SecondaryProfessionalQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondaryProfessionalQualificationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionalQualificationFilterDTO;
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
public class SecondaryProfessionalQualificationSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, SecondaryProfessionalQualificationEntity, SecondaryProfessionalQualificationFilterDTO> implements SecondaryProfessionalQualificationSearchRepository {

    @Override
    protected Class<SecondaryProfessionalQualificationEntity> getEntityClass() {
        return SecondaryProfessionalQualificationEntity.class;
    }

    @Override
    protected void additionalSearchQuery(SecondaryProfessionalQualificationFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String code = filter.getCode();
        if (StringUtils.hasText(code)) {
            queryBuilder.append(" AND LOWER(r.code) like LOWER(:code) ");
            queryParameters.put("code", "%" + code + "%");
        }

        Integer professionGroupCode = filter.getProfessionGroupCode();
        if (Objects.nonNull(professionGroupCode)) {
            queryBuilder.append(" AND r.professionGroup.id =:professionGroupCode ");
            queryParameters.put("professionGroupCode", professionGroupCode);
        }
    }
}
