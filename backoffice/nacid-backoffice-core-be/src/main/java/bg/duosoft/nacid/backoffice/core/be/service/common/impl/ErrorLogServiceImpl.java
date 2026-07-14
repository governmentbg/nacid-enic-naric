package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.repository.common.ErrorLogRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ErrorLogService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogResolutionDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ErrorLogMapper;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository repository;
    private final ErrorLogMapper mapper;

    @Override
    @LogObjectChange(id = "#result.id", before = "#root.target.selectById(#id)", after = "#result", operation = "'update'")
    public ErrorLogDTO resolveErrorLog(Integer id, ErrorLogResolutionDTO resolutionDto) {
        ErrorLogDTO errorLogDTO = selectById(id);
        if (Objects.isNull(errorLogDTO)) {
            throw new RuntimeException("Cannot find error log record with id = " + id);
        }

        LocalDateTime resolvedDate = errorLogDTO.getResolvedDate();
        if (Objects.nonNull(resolvedDate)) {
            throw new RuntimeException("Cannot resolve error log record, because it has been already resolved ! ID: " + id);
        }

        errorLogDTO.setResolvedComment(resolutionDto.getComment());
        errorLogDTO.setResolvedDate(LocalDateTime.now());
        errorLogDTO.setResolvedUser(SecurityUtils.getUsername());
        return mapper.toDto(repository.save(mapper.toEntity(errorLogDTO)));
    }

    @Override
    public ErrorLogDTO selectById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<ErrorLogDTO> searchRecords(ErrorLogFilterDTO filter) {
        return mapper.toDtoList(repository.searchRecords(filter));
    }

    @Override
    public int getRecordsCount(ErrorLogFilterDTO filter) {
        return repository.getRecordsCount(filter);
    }

    @Override
    public Integer selectUnresolvedCount() {
        ErrorLogFilterDTO filter = new ErrorLogFilterDTO();
        filter.setOnlyUnresolved(true);
        return repository.getRecordsCount(filter);
    }

    @Override
    public List<Integer> selectUnresolvedIdentifiers() {
        return repository.selectUnresolvedIdentifiers();
    }
}
