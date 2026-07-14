package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgAppStatusRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgSarAppStatusRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgSarAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgAppStatusMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgSarAppStatusMapper;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:45
 */
@Service
@RequiredArgsConstructor
public class CfgAppStatusService {

    private final CfgAppStatusRepository cfgAppStatusRepository;
    private final CfgAppStatusMapper cfgAppStatusMapper;
    private final CfgSarAppStatusRepository cfgSarAppStatusRepository;
    private final CfgSarAppStatusMapper cfgSarAppStatusMapper;


    public void deleteAll() {
        cfgSarAppStatusRepository.deleteAll();
        cfgAppStatusRepository.deleteAll();
    }

    public CfgAppStatusDTO insert(CfgAppStatusDTO dto) {
        checkIsForInsert(dto::getId);

        CfgAppStatusEntity e = cfgAppStatusMapper.toEntity(dto);
        e = cfgAppStatusRepository.save(e);
        return cfgAppStatusMapper.toDto(e);
    }

    public CfgSarAppStatusDTO insert(CfgSarAppStatusDTO dto) {

        CfgSarAppStatusEntity e = cfgSarAppStatusMapper.toEntity(dto);
        e = cfgSarAppStatusRepository.save(e);
        return cfgSarAppStatusMapper.toDto(e);
    }

    @Cacheable(value = "CfgAppStatusService", key = "'application-statuses-' + #applicationType + '-' + #onlyActive")
    public List<CfgAppStatusDTO> selectByApplicationType(String applicationType, boolean onlyActive) {
        return cfgAppStatusMapper.toDtoList(cfgAppStatusRepository.getByApplicationType(applicationType, onlyActive));
    }

    @Cacheable(value = "CfgAppStatusService", key = "'application-statuses-' + #applicationType + '-'+ #applicationSubType + '-' + #onlyActive")
    public List<CfgAppStatusDTO> selectByApplicationTypeAndSubType(String applicationType, String applicationSubType, boolean onlyActive) {
        return cfgAppStatusMapper.toDtoList(cfgAppStatusRepository.getByApplicationTypeSubtype(applicationType, applicationSubType, onlyActive, null));
    }

    @Cacheable(value = "CfgAppStatusService", key = "'commission-statuses-' + #onlyActive")
    public List<CfgAppStatusDTO> selectCommissionStatuses(boolean onlyActive) {
        return cfgAppStatusMapper.toDtoList(cfgAppStatusRepository.getByCommissionFlagTrue(onlyActive));
    }

    @Cacheable(value = "CfgAppStatusService", key = "'legal-statuses-' +  #applicationType + '-' + #onlyActive")
    public List<CfgAppStatusDTO> selectLegalStatuses(String applicationType, String applicationSubType, boolean onlyActive) {
        return cfgAppStatusMapper.toDtoList(cfgAppStatusRepository.getByLegalFlagTrueAndApplicationType(applicationType, applicationSubType, onlyActive));
    }

    @Cacheable(value = "CfgAppStatusService", key = "'all-sar-app-status-configs'")
    public List<CfgSarAppStatusDTO> selectAllSarAppStatuses() {
        List<CfgSarAppStatusEntity> all = cfgSarAppStatusRepository.findAll();
        return cfgSarAppStatusMapper.toDtoList(all);
    }

    @Cacheable(value = "CfgAppStatusService", key = "'sar-app-status-configs' + #statusCode")
    public List<CfgSarAppStatusDTO> selectSarAppConfigByStatus(String statusCode) {
        List<CfgSarAppStatusEntity> statusConfigs = cfgSarAppStatusRepository.getAllByStatusPkId(statusCode);
        return cfgSarAppStatusMapper.toDtoList(statusConfigs);
    }

    public List<CfgAppStatusDTO> selectNormalStatuses(String applicationType, String applicationSubType, boolean onlyActive, String currentStatus) {
        return cfgAppStatusMapper.toDtoList(cfgAppStatusRepository.getByApplicationTypeSubtype(applicationType, applicationSubType, onlyActive, currentStatus));
    }

    private void checkIsForInsert(Supplier<Object> checkFunction) {
        if (checkFunction.get() != null) {
            throw new BadRequestException("id not null - " + checkFunction.get());
        }
    }


}
