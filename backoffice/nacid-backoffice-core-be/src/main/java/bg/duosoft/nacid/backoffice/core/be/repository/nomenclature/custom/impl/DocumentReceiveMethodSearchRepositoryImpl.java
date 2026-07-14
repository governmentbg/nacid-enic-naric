package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.DocumentReceiveMethodSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveMethodEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentReceiveMethodFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DocumentReceiveMethodSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, DocumentReceiveMethodEntity, DocumentReceiveMethodFilterDTO> implements DocumentReceiveMethodSearchRepository {

    @Override
    protected Class<DocumentReceiveMethodEntity> getEntityClass() {
        return DocumentReceiveMethodEntity.class;
    }

    @Override
    protected void additionalSearchQuery(DocumentReceiveMethodFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        Boolean hasDocumentRecipient = filter.getHasDocumentRecipient();
        if (Objects.nonNull(hasDocumentRecipient)) {
            queryBuilder.append(" AND r.documentRecipientFlag = :documentRecipientFlag ");
            queryParameters.put("documentRecipientFlag", hasDocumentRecipient ? 1 : 0);
        }

        Boolean eservicesRequirePaymentReceipt = filter.getEservicesRequirePaymentReceipt();
        if (Objects.nonNull(eservicesRequirePaymentReceipt)) {
            queryBuilder.append(" AND r.eservicesRequirePaymentReceiptFlag = :eservicesRequirePaymentReceiptFlag ");
            queryParameters.put("eservicesRequirePaymentReceiptFlag", eservicesRequirePaymentReceipt ? 1 : 0);
        }
    }

}
