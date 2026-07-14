package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.mapper.common.ChangeRecordLogSimpleMapper;
import bg.duosoft.nacid.backoffice.core.be.repository.common.ChangeRecordLogRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ChangeRecordLogService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgSarAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.summary.MultipleAppDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ChangeRecordLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChangeRecordLogServiceImpl implements ChangeRecordLogService {

    private final ChangeRecordLogRepository changeRecordLogRepository;
    private final ChangeRecordLogMapper changeRecordLogMapper;
    private final ChangeRecordLogSimpleMapper changeRecordLogSimpleMapper;

    @Override
    public ChangeRecordLogDTO selectById(Integer id) {
        return changeRecordLogMapper.toDto(changeRecordLogRepository.findById(id).orElse(null));
    }

    @Override
    public List<ChangeRecordLogSimpleDTO> selectByApplicationName(String applicationName, Integer page, Integer pageSize) {
        return changeRecordLogSimpleMapper.toDtoList(changeRecordLogRepository.selectByApplicationName(applicationName, page, pageSize));
    }

    @Override
    public List<ChangeRecordLogSimpleDTO> searchRecords(ChangeRecordLogFilterDTO filter) {
        return changeRecordLogSimpleMapper.toDtoList(changeRecordLogRepository.searchRecords(filter));
    }

    @Override
    public int getRecordsCount(ChangeRecordLogFilterDTO filter) {
        return changeRecordLogRepository.getRecordsCount(filter);
    }

    @Override
    @Cacheable(value = "ChangeRecordLogService", key = "'service-dictionary' + #applicationName")
    public List<StringKeyNomenclatureBase> selectServiceDictionary(String applicationName) {
        List<List<Object>> result = changeRecordLogRepository.selectServiceDictionary(applicationName);
        return result.stream().map(x -> {
            StringKeyNomenclatureBase record = new StringKeyNomenclatureBase();
            record.setId((String) x.get(0));
            record.setName((String) x.get(1));
            record.setIsActive(Objects.nonNull(x.get(2)) && x.get(2).toString().equals("1"));
            return record;
        }).toList();
    }
}
