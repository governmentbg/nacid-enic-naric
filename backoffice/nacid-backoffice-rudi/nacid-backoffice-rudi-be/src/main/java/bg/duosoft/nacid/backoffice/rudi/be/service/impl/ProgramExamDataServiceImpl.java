package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.program.ProgramExamSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.program.ProgramExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.ProgramExamDataService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramExamDataServiceImpl implements ProgramExamDataService {
    private final ProgramExamDataMapper programExamDataMapper;
    private final RudiApplicationService rudiApplicationService;
    private final RudiStatusService rudiStatusService;

    @Override
    public ProgramExamSectionDTO saveProgramExamData(ProgramExamSectionDTO programExamination, RudiApplicationDTO rudiApplication) {
        programExamDataMapper.overrideApplicationData(programExamination, rudiApplication);
        rudiApplicationService.save(rudiApplication, ValidationScope.PROGRAM_EXAMINATION);

        if (programExamination.getIsLegitimate()) {
            if (!ApplicationStatusType.LEGITIMATE_PROGRAM.code().equals(rudiApplication.getApplication().getStatus().getId())) {
                rudiStatusService.insertRudiStatus(rudiApplication.getApplication().getId(), InsertStatusDTO.builder().applicationId(rudiApplication.getApplication().getId()).statusId(ApplicationStatusType.LEGITIMATE_PROGRAM.code()).build());
                programExamination.setIsStatusUpdated(true);
            }
        }

        return programExamination;
    }
}
