package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.GraduationDocumentTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GraduationDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.GraduationDocumentTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GraduationDocumentTypeSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, GraduationDocumentTypeEntity, GraduationDocumentTypeFilterDTO> implements GraduationDocumentTypeSearchRepository {

    @Override
    protected Class<GraduationDocumentTypeEntity> getEntityClass() {
        return GraduationDocumentTypeEntity.class;
    }
}
