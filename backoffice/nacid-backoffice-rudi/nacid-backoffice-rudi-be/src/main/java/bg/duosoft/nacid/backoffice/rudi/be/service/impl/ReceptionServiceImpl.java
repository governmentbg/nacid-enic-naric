package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocCreation;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocSourceType;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsService;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.externalnomenclaturesmap.AdminExternalNomenclaturesMapClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureSystem;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationDataService;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReceptionInserterService;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReceptionService;
import bg.duosoft.nacid.backoffice.rudi.be.util.reception.DocCreationConverter;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.RudiApplicationValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
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
public class ReceptionServiceImpl implements ReceptionService {

    private final AbdocsService abdocsService;
    private final RudiApplicationValidator validator;
    private final DocCreationConverter docCreationConverter;
    private final ApplicationDataService applicationDataService;
    private final ReceptionInserterService rudiReceptionInserterService;
    private final AdminExternalNomenclaturesMapClient adminExternalNomenclaturesMapClient;

    @Override
    public RudiApplicationDTO createReception(RudiApplicationDTO receptionApp) {
        applicationDataService.fillFullPersonAndAddressData(receptionApp);

        List<ValidationError> errors = validator.validate(receptionApp, ValidationScope.RECEPTION);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        DocCreation docCreation = docCreationConverter.convertObject(receptionApp.getApplication(), DocSourceType.Counter, adminExternalNomenclaturesMapClient.getBySystemAndNomenclatureType(ExternalNomenclatureSystem.ABDOCS.code(), ExternalNomenclatureType.ABDOCS_SETTLEMENT.code()));
        Doc document = abdocsService.createDocument(docCreation);
        return rudiReceptionInserterService.insertApplication(receptionApp, document, false);
    }

}
