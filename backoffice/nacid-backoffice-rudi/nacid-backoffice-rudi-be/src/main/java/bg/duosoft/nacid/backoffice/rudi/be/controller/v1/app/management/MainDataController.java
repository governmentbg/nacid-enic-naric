package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.domain.factory.RudiMainDataObjectFactory;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.provider.RudiMainDataMapperProvider;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidbackofficeshareddata.utils.ResponsibleUserChangeUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATIONS)
@RequestMapping("/api/v1/applications/data/main")
public class MainDataController extends RudiAppDataBaseController {

    private final RudiMainDataMapperProvider mapperProvider;
    private final RudiMainDataObjectFactory objectFactory;
    private final MailSenderService mailSenderService;


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select rudi main data")
    public RudiMainDataBaseDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        return mapperProvider.getMapper(app).toMainDataSection(app);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update rudi main data")
    public void updateApplicationMainData(@PathVariable Integer id, @RequestBody String requestData) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        RudiMainDataBaseDTO mainDataDto = objectFactory.createObject(app, requestData);
        boolean responsibleUserChange = ResponsibleUserChangeUtils.isResponsibleUserChange(mainDataDto.getResponsibleUser(), app.getApplication().getResponsibleUsers());
        mapperProvider.getMapper(app).overrideApplicationData(mainDataDto, app);
        rudiApplicationService.save(app, ValidationScope.MAIN_DATA);
        if (responsibleUserChange) {
            String appEntryNumber = AbdocsNumbersUtils.buildRegistrationNumber(app.getApplication().getEntryNumber(), app.getApplication().getEntryDate());
            ResponsibleUserChangeUtils.sendResponsibleUserChangeNotification(mainDataDto.getResponsibleUser(), appEntryNumber, SecurityUtils.getUsername(), mailSenderService);
        }
    }

}
