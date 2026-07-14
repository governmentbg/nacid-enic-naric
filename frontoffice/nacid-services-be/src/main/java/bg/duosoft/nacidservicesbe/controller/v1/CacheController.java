package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidshared.web.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CACHE)
@RequestMapping("/api/v1/cache")
public class CacheController {
    private final CacheService cacheService;

    @GetMapping(value = "/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Clear application cache")
    public void clearAll() {
        cacheService.clearCache();
    }

}
