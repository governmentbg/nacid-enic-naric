package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.DocumentReceiveMethodSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocumentReceiveMethodEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocumentReceiveMethodDataFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DocumentReceiveMethodSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, DocumentReceiveMethodEntity, DocumentReceiveMethodDataFilterDTO> implements DocumentReceiveMethodSearchRepository {

    @Override
    protected Class<DocumentReceiveMethodEntity> getEntityClass() {
        return DocumentReceiveMethodEntity.class;
    }

    @Override
    protected void additionalSearchQuery(DocumentReceiveMethodDataFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        Boolean documentRecipient = filter.getDocumentRecipient();
        if (Objects.nonNull(documentRecipient)) {
            queryBuilder.append(" AND r.documentRecipient = :documentRecipient ");
            queryParameters.put("documentRecipient", documentRecipient ? 1 : 0);
        }
    }

}
