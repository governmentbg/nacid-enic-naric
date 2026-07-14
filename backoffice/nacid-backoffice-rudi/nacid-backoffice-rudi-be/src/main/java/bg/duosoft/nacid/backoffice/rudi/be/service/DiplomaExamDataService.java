package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.diploma.DiplomaExamSectionDTO;

public interface DiplomaExamDataService {
    DiplomaExamSectionDTO saveDiplomaExamData(DiplomaExamSectionDTO diplomaExamination, RudiApplicationDTO rudiApplication);
}
