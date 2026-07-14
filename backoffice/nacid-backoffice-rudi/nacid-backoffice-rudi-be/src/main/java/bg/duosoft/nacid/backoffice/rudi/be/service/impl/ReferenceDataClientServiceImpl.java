package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata.ReferenceDataClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReferenceDataClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceDataClientServiceImpl implements ReferenceDataClientService {

    private final ReferenceDataClient referenceDataClient;

    @Override
    @Cacheable(value = "ReferenceDataClientService", key = "'allByDomain-'+#domain.domain()")
    public List<ReferenceDataDTO> selectAllByDomain(ReferenceDataDomain domain) {
        return referenceDataClient.selectAll(domain.domain(), false);
    }
}
