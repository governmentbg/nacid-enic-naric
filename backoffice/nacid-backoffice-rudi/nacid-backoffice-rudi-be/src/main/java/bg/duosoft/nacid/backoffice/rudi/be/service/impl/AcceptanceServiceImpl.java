package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocCreation;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocSourceType;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.AcceptanceService;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReceptionInserterService;
import bg.duosoft.nacid.backoffice.rudi.be.util.reception.DocCreationConverter;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.RudiApplicationValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidbackofficeshareddata.service.FoAcceptAppExtraDataService;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcceptanceServiceImpl implements AcceptanceService {

    private final RudiApplicationValidator validator;
    private final ReceptionInserterService rudiReceptionInserterService;
    private final FoAcceptAppExtraDataService foAcceptAppExtraDataService;

    @Override
    public RudiApplicationDTO acceptApplication(RudiApplicationDTO receptionApp) {
        List<ValidationError> errors = validator.validate(receptionApp, ValidationScope.E_APPS_ACCEPTANCE);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        RudiApplicationDTO insertedApplication = rudiReceptionInserterService.insertApplication(receptionApp, null, true);
        foAcceptAppExtraDataService.updateFrontOfficeData(insertedApplication.getApplication());
        foAcceptAppExtraDataService.updatePaymentsData(insertedApplication.getApplication());
        return insertedApplication;
    }

}
