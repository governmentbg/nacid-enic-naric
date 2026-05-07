package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.LanguageSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LanguageEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LanguageFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LanguageSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, LanguageEntity, LanguageFilterDTO> implements LanguageSearchRepository {

    @Override
    protected Class<LanguageEntity> getEntityClass() {
        return LanguageEntity.class;
    }

}
