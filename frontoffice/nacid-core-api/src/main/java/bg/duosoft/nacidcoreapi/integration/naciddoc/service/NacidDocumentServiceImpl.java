package bg.duosoft.nacidcoreapi.integration.naciddoc.service;

import bg.duosoft.nacidcoreapi.integration.naciddoc.client.NacidDocumentClient;
import bg.duosoft.nacidcoreapi.integration.naciddoc.domain.NacidDocument;
import bg.duosoft.nacidcoreapi.integration.naciddoc.domain.NacidDocumentDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.03.2024
 * Time: 18:52
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NacidDocumentServiceImpl implements NacidDocumentService {

    private final NacidDocumentClient nacidDocumentClient;

    @Override
    public NacidDocument getNacidDescriptionDocument(String id) {
        NacidDocument document = null;
        try {
            NacidDocumentDetails details = nacidDocumentClient.getNacidDocumentDetails("bg", "block", id);
            if (details != null && details.getDocuments() != null) {
                document = details.getDocuments().stream().filter(doc -> "opisanie".equals(doc.getType())).findFirst().orElse(null);
            }
            return document;
        } catch (Exception e){
            log.error("Failed to get document from nacid service", e);
            return null;
        }
    }

    @Override
    public byte[] getNacidDocumentBytes(String path) {
        try {
            return nacidDocumentClient.getDocumentBytes(path);
        } catch (Exception e){
            log.error("Failed to get document content from nacid service", e);
            return null;
        }
    }
}
