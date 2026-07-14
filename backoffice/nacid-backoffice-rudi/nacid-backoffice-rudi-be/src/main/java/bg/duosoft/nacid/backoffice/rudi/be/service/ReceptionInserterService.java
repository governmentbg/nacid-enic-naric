package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;

public interface ReceptionInserterService {

    RudiApplicationDTO insertApplication(RudiApplicationDTO receptionApp, Doc document, boolean isFoAppAccept);

}
