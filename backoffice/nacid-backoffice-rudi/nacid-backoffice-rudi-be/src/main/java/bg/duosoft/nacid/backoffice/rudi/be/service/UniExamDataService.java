package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSubsectionDTO;

public interface UniExamDataService {
    UniExamSubsectionDTO saveUniExamData(UniExamSubsectionDTO universityExamination, RudiApplicationDTO application);
    void overrideAttachedDocsWithOriginal(UniExamSubsectionDTO uniExamSubsection, RudiApplicationDTO application);
}
