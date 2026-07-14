package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.cache;

import bg.duosoft.nacid.backoffice.abdocs.config.security.SecurityTokenHolder;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/clear/abdocs-tokens")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Clear abdocs tokens cache")
    public void clearAbdocsTokens() {
        SecurityTokenHolder.getInstance().clearMap();
    }

}
