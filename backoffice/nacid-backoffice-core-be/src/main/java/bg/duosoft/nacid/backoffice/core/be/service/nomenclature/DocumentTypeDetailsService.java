package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.DocumentTypeToDocumentCategoryRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentTypeDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentTypeDetailsService {
    private final DocumentTypeToDocumentCategoryRepository documentTypeToDocumentCategoryRepository;
    private final DocumentTypeDetailsMapper documentTypeDetailsMapper;

    public List<DocumentTypeDetailDTO> selectDocumentTypeDetails(Integer docType, String docCategory,
                                                      String applicationType,
                                                      String applicationSubType) {
        return documentTypeDetailsMapper.toDtoList(documentTypeToDocumentCategoryRepository.selectDocumentTypeDetails(docType, docCategory, applicationType, applicationSubType));

    }
}
