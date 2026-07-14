package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberStatementDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberStatementEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.ApplicationCommissionMemberStatementMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.ApplicationCommissionMemberStatementRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationCommissionMemberStatementService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationCommissionMemberStatementServiceImpl implements ApplicationCommissionMemberStatementService {
    private final ApplicationCommissionMemberStatementRepository applicationCommissionMemberStatementRepository;
    private final ApplicationCommissionMemberStatementMapper applicationCommissionMemberStatementMapper;
    private final RudiApplicationService rudiApplicationService;

    @Override
    public List<ApplicationCommissionMemberStatementDTO> selectByApplicationId(Integer applicationId) {
        return applicationCommissionMemberStatementMapper.toDtoList(applicationCommissionMemberStatementRepository.selectByApplicationId(applicationId));
    }

    @Override
    public ApplicationCommissionMemberStatementDTO selectById(Integer id) {
        ApplicationCommissionMemberStatementEntity applicationCommissionMemberStatementEntity = applicationCommissionMemberStatementRepository.findById(id).orElse(null);
        if (Objects.isNull(applicationCommissionMemberStatementEntity)) {
            throw new ResourceNotFoundException();
        }
        return applicationCommissionMemberStatementMapper.toDto(applicationCommissionMemberStatementEntity);
    }

    @Override
    public void delete(Integer id) {
        applicationCommissionMemberStatementRepository.deleteById(id);
    }

    @Override
    public RudiApplicationDTO saveApplicationCommissionMemberData(Integer applicationId, ApplicationCommissionMemberStatementDTO requestData) {
        RudiApplicationDTO application = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }

        if (CollectionUtils.isEmpty(application.getApplicationCommissionMemberStatements())) {
            application.setApplicationCommissionMemberStatements(new ArrayList<>());
        }
        List<ApplicationCommissionMemberStatementDTO> statements = application.getApplicationCommissionMemberStatements();
        if (Objects.nonNull(requestData.getId())) {
            ApplicationCommissionMemberStatementDTO existedRecord = statements.stream().filter(r -> r.getId().equals(requestData.getId())).findFirst().orElse(null);
            int existedRecordIndex = statements.indexOf(existedRecord);
            statements.set(existedRecordIndex, requestData);
        } else {
            statements.add(requestData);
        }
        return rudiApplicationService.save(application, ValidationScope.COMMISSION_MEMBER_STATEMENT);
    }
}
