package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacidshared.web.controller.CrudController;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPropertiesService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.APPLICATION_PROPERTIES_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.APPLICATION_PROPERTIES_EDIT;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 14:16
 */
@Slf4j
@RestController
@Api(tags = Tags.APPLICATION_PROPERTIES)
@RequestMapping("/api/v1/application-properties")
@RequiredArgsConstructor
public class ApplicationPropertiesController extends CrudController<String, ApplicationPropertyDTO> {
    private final ApplicationPropertiesService service;
    @Override
    protected CrudServiceBaseImpl<String, ApplicationPropertyDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return APPLICATION_PROPERTIES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return APPLICATION_PROPERTIES_ACCESS;
    }
}
