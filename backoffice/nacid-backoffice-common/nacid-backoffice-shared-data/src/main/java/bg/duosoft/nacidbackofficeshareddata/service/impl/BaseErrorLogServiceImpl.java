package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ErrorLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ErrorLogMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseErrorLogRepository;
import bg.duosoft.nacidbackofficeshareddata.service.BaseErrorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BaseErrorLogServiceImpl implements BaseErrorLogService {

    private final ErrorLogMapper mapper;
    private final BaseErrorLogRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ErrorLogDTO insert(ErrorLogType type, String errorMessage, String dataJSON) {
        ErrorLogEntity requestEntity = new ErrorLogEntity();
        requestEntity.setErrorType(type.code());
        requestEntity.setErrorMessage(errorMessage);
        requestEntity.setDataJson(dataJSON);

        ErrorLogEntity insertedEntity = repository.insertRecord(requestEntity);
        return mapper.toDto(insertedEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ErrorLogDTO insert(ErrorLogType type, String errorMessage, String dataJSON, String referenceId) {
        ErrorLogEntity requestEntity = new ErrorLogEntity();
        requestEntity.setErrorType(type.code());
        requestEntity.setErrorMessage(errorMessage);
        requestEntity.setDataJson(dataJSON);
        requestEntity.setReferenceId(referenceId);

        ErrorLogEntity insertedEntity = repository.insertRecord(requestEntity);
        return mapper.toDto(insertedEntity);
    }

    @Override
    public ErrorLogDTO resolve(Integer id, String resolvedComment, String resolvedUser) {
        ErrorLogEntity entity = repository.selectById(id);
        if (Objects.isNull(entity)) {
            throw new RuntimeException("[ERROR LOG] Cannot resolve error log, because record is not found in database ! ID: " + id);
        }

        LocalDateTime resolvedDate = entity.getResolvedDate();
        if (Objects.nonNull(resolvedDate)) {
            throw new RuntimeException("[ERROR LOG] Cannot resolve error log, because resolved date is filled in database! ID: " + id);
        }

        entity.setResolvedUser(resolvedUser);
        entity.setResolvedComment(resolvedComment);
        ErrorLogEntity updatedEntity = repository.resolveRecord(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public ErrorLogDTO selectByReferenceIdAndType(ErrorLogType type, String referenceId) {
        if (!StringUtils.hasText(referenceId) || Objects.isNull(type)) {
            return null;
        }

        ErrorLogEntity entity = repository.selectByReferenceIdAndType(type, referenceId);
        return mapper.toDto(entity);
    }
}
