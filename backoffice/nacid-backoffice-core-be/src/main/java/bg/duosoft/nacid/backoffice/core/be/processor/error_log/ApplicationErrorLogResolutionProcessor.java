package bg.duosoft.nacid.backoffice.core.be.processor.error_log;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.fo.FoAppService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class ApplicationErrorLogResolutionProcessor extends ErrorLogResolutionProcessor {

    @Autowired
    protected FoAppService foAppService;

    @Autowired
    protected ApplicationsService applicationsService;

    @NotNull
    protected CommonApplicationDTO selectFoApp(ErrorLogDTO errorLog, ApplicationDTO boApplication) {
        Integer efilingId = boApplication.getEfilingId();
        if (Objects.isNull(efilingId)) {
            throw new InternalServerErrorException("[ERROR LOG] Empty efilingId! ErrorLogId: " + errorLog.getId() + ", ApplicationId: " + boApplication.getId());
        }

        String appType = boApplication.getApplicationType().getId();
        String appSubType = boApplication.getApplicationSubtype().getId();

        CommonApplicationDTO foApplication = foAppService.selectFoApplication(efilingId, appType, appSubType);
        if (Objects.isNull(foApplication)) {
            throw new InternalServerErrorException("[ERROR LOG] Empty front-office application! ErrorLogId: " + errorLog.getId() + ", EfilingId: " + efilingId);
        }

        return foApplication;
    }

    protected ApplicationDTO selectBoApp(ErrorLogDTO errorLog, Integer boId) {
        ApplicationDTO boApplication = applicationsService.getApplicationById(boId);
        if (Objects.isNull(boApplication)) {
            throw new InternalServerErrorException("[ERROR LOG] Empty back-office application! ErrorLogId: " + errorLog.getId());
        }

        return boApplication;
    }

}
