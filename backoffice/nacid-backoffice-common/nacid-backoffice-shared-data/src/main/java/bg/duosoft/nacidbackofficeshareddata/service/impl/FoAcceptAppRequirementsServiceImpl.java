package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationService;
import bg.duosoft.nacidbackofficeshareddata.service.FoAcceptAppRequirementsService;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoAcceptAppRequirementsServiceImpl implements FoAcceptAppRequirementsService {

    private final BaseApplicationService baseApplicationService;

    @Override
    public void checkAcceptRequirements(CommonApplicationDTO foApplication, ApplicationSubType subType) {
        Integer foAppId = foApplication.getId();

        ApplicationType applicationType = foApplication.getApplicationType();
        if (Objects.isNull(applicationType)) {
            throw new InternalServerErrorException("Application type is empty! Front-office application ID: " + foAppId);
        }

        if (!applicationType.getCode().equals(subType.appType())) {
            throw new InternalServerErrorException("Wrong application type! Front-office application ID: " + foAppId + ", AppType: " + applicationType.getCode());
        }

        ApplicationSubtype applicationSubtype = foApplication.getApplicationSubtype();
        if (Objects.isNull(applicationSubtype)) {
            throw new InternalServerErrorException("Application subType is empty! Front-office application ID: " + foAppId);
        }

        if (!applicationSubtype.getCode().equals(subType.appSubType())) {
            throw new InternalServerErrorException("Wrong application subType! Front-office application ID: " + foAppId + ", AppSubType: " + applicationSubtype.getCode());
        }

        checkIfFoAppIsInStatusForAcceptance(foApplication);

        boolean isAccepted = baseApplicationService.isFoAppAlreadyAccepted(foAppId);
        if (isAccepted) {
            throw new InternalServerErrorException("FO Application with ID: " + foAppId + " has been already accepted !");
        }
    }

    private void checkIfFoAppIsInStatusForAcceptance(CommonApplicationDTO foApplication) {
        if (Objects.isNull(foApplication)) {
            throw new InternalServerErrorException("FO Application is not in status for acceptance !");
        }

        String status = foApplication.getFoStatus().getCode();
        if (!StringUtils.hasText(status)) {
            throw new InternalServerErrorException("FO Application status is empty ! FO ID: " + foApplication.getId());
        }

        boolean isForAcceptance = status.equals(FoApplicationStatus.SUBMITTED.getCode()) || status.equals(FoApplicationStatus.SUBMITTED_WITH_SIGNATURE.getCode());
        if (!isForAcceptance) {
            throw new InternalServerErrorException("FO Application is not in status for acceptance ! FO ID: " + foApplication.getId());
        }
    }
}
