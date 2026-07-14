package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgappstatus.CfgAppStatusClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.AppStatusClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppStatusClientServiceImpl implements AppStatusClientService {

    private final CfgAppStatusClient cfgAppStatusClient;

    @Override
    @Cacheable(value = "AppStatusClientService", key = "'all-sar-app-status-configs'")
    public List<CfgSarAppStatusDTO> selectAllSarStatusConfigs() {
        try {
            return cfgAppStatusClient.getAllSarStatusConfigs();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
