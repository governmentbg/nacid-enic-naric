package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.EqualizationSubjectDto;

import java.util.List;

public interface GradesEqualizationService {

    List<EqualizationSubjectDto> gradeEqualization(DiplomaDetailsDto diplomaDetails);
}
