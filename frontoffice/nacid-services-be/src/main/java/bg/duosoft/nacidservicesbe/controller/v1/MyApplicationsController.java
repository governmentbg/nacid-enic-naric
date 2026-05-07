package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListFilterDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListRecordDTO;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.ApplicationListFilterValidator;
import bg.duosoft.nacidshareddata.exception.UnauthorizedException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 15:25
 */
@Api(tags = Tags.MY_APPLICATIONS)
@RestController
@RequestMapping("/api/v1/my-applications")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class MyApplicationsController {

    private final CommonApplicationService commonApplicationService;
    private final ApplicationListFilterValidator applicationListFilterValidator;

    @PostMapping("/filter")
    public Page<ApplicationListRecordDTO> filterApplications(@RequestBody ApplicationListFilterDTO listFilter){
        BadRequestValidator.validateRequest(applicationListFilterValidator, listFilter);
        String username = SecurityUtils.getUsername();
        if(StringUtils.hasText(username)) {
            listFilter.setUser(username);
            List<ApplicationListRecordDTO> resultList = commonApplicationService.getAllApplications(listFilter);
            Integer total = commonApplicationService.getTotalApplications(listFilter);
            return new Page<>(total, resultList, listFilter.getPageSize());
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/statuses")
    public List<String> getMyApplicationsStatuses(){
        return commonApplicationService.getAllLastStatusesByUser(SecurityUtils.getUsername());
    }
}
