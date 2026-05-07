package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.diploma.DiplomaExamSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.diploma.DiplomaExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.DiplomaExamDataService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiplomaExamDataServiceImpl implements DiplomaExamDataService {
    private final DiplomaExamDataMapper diplomaExamDataMapper;
    private final RudiApplicationService rudiApplicationService;
    private final RudiStatusService rudiStatusService;
    @Override
    public DiplomaExamSectionDTO saveDiplomaExamData(DiplomaExamSectionDTO diplomaExamination, RudiApplicationDTO rudiApplication) {
        diplomaExamDataMapper.overrideApplicationData(diplomaExamination, rudiApplication);
        RudiApplicationDTO savedApp = rudiApplicationService.save(rudiApplication, ValidationScope.DIPLOMA_EXAMINATION);
        DiplomaExamSectionDTO savedDiplomaExamSection = diplomaExamDataMapper.toDiplomaExamSection(savedApp);

        if (diplomaExamination.getIsAuthentic()) {
            if (!ApplicationStatusType.AUTHENTIC.code().equals(rudiApplication.getApplication().getStatus().getId())) {
                rudiStatusService.insertRudiStatus(rudiApplication.getApplication().getId(), InsertStatusDTO.builder().applicationId(rudiApplication.getApplication().getId()).statusId(ApplicationStatusType.AUTHENTIC.code()).build());
                savedDiplomaExamSection.setIsStatusUpdated(true);
            }
        }

        return savedDiplomaExamSection;
    }
}
