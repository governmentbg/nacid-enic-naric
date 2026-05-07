package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;

public interface AbdocsMissingDocumentService {

    Doc generateDocument(Integer applicationId);
}
