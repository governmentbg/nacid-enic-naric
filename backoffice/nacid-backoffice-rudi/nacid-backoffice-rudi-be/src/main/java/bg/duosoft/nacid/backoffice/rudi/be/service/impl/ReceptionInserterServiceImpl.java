package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.util.reception.ReceptionUtils;
import bg.duosoft.nacid.backoffice.rudi.be.service.AcceptanceInserterService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReceptionInserterService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidbackofficeshareddata.exception.ReceptionException;
import bg.duosoft.nacidbackofficeshareddata.service.BaseStatusService;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceptionInserterServiceImpl implements ReceptionInserterService {
    private static final String INITIAL_NORMAL_STATUS_CODE = "FILE";
    private static final String INITIAL_DOCFLOW_STATUS_CODE = "POS";

    private final RudiApplicationService rudiApplicationService;
    private final BaseStatusService baseStatusService;
    private final AbdocsAdminService abdocsAdminService;
    private final AcceptanceInserterService acceptanceInserterService;

    @Override
    public RudiApplicationDTO insertApplication(RudiApplicationDTO receptionApp, Doc document, boolean isFoAppAccept) {
        try {
            ReceptionUtils.setApplicationPredefinedData(receptionApp.getApplication(), document, INITIAL_NORMAL_STATUS_CODE, INITIAL_DOCFLOW_STATUS_CODE, isFoAppAccept);

            if (isFoAppAccept) {
                acceptanceInserterService.insertData(receptionApp);
            }

            RudiApplicationDTO save = rudiApplicationService.save(receptionApp, ValidationScope.NO_VALIDATION);

            baseStatusService.insertInitialStatusHistoryRecords(InsertStatusDTO.builder()
                    .applicationId(save.getApplication().getId())
                    .statusId(INITIAL_NORMAL_STATUS_CODE)
                    .docflowStatusId(INITIAL_DOCFLOW_STATUS_CODE)
                    .build(), SecurityUtils.getUsername());

            return save;
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            if (!isFoAppAccept) {
                Integer docId = document.getDocId();
                try {
                    abdocsAdminService.deleteDocument(docId);
                } catch (Exception ex) {
                    log.error("[ABDOCS] Cannot delete document with id " + docId);
                    log.error(e.getMessage(), e);
                }
            }

            throw new ReceptionException(e.getMessage(), e);
        }
    }



}
