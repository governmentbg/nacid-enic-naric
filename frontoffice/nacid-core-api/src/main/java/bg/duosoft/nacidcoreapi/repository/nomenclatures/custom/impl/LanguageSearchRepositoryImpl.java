package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.LanguageSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.LanguageFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:47
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LanguageSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, LanguageEntity, LanguageFilterDTO> implements LanguageSearchRepository {

    @Override
    protected Class<LanguageEntity> getEntityClass() {
        return LanguageEntity.class;
    }
}
