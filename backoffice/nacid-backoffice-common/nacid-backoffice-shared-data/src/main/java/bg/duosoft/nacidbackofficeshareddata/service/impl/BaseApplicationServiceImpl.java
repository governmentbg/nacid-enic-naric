package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseApplicationRepository;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseApplicationServiceImpl implements BaseApplicationService {
    private final BaseApplicationRepository baseApplicationRepository;
    private final ApplicationMapper applicationMapper;


    public ApplicationDTO selectApplicationById(Integer id) {
        ApplicationEntity application = baseApplicationRepository.selectApplicationById(id);
        return applicationMapper.toDto(application);
    }

    public ApplicationDTO selectApplicationByEntryNumberAndDate(String entryNumber, LocalDate entryDate) {
        ApplicationEntity application = baseApplicationRepository.selectApplicationByEntryNumberAndDate(entryNumber, entryDate);
        return applicationMapper.toDto(application);
    }

    @Override
    public boolean isFoAppAlreadyAccepted(Integer efilingId) {
        return baseApplicationRepository.isFoAppAlreadyAccepted(efilingId);
    }

    @Override
    public LocalDate getExecutionPeriodEnd(Integer applicationId) {
        return baseApplicationRepository.getExecutionPeriodEnd(applicationId);
    }
}
