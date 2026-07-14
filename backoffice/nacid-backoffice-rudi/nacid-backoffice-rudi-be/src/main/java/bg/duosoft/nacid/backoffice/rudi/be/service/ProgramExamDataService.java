package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.program.ProgramExamSectionDTO;

public interface ProgramExamDataService {
    ProgramExamSectionDTO saveProgramExamData(ProgramExamSectionDTO programExamination, RudiApplicationDTO rudiApplication);
}
