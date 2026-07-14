package bg.duosoft.nacidcoreapi.integration.naciddoc.service;

import bg.duosoft.nacidcoreapi.integration.naciddoc.domain.NacidDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.03.2024
 * Time: 18:51
 */
public interface NacidDocumentService {

    NacidDocument getNacidDescriptionDocument(String id);
    byte[] getNacidDocumentBytes(String path);
}
