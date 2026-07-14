package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.PersonalNacidIdRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonalNacidIdService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonalNacidIdEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonalNacidIdDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.PersonalNacidIdMapper;
import bg.duosoft.nacidshareddata.util.random.RandomNumberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonalNacidIdServiceImpl implements PersonalNacidIdService {

    private final PersonalNacidIdRepository personalNacidIdRepository;
    private final PersonalNacidIdMapper personalNacidIdMapper;

    @Override
    public PersonalNacidIdDTO selectByValue(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }

        PersonalNacidIdEntity personalNacidIdEntity = personalNacidIdRepository.findById(value).orElse(null);
        return personalNacidIdMapper.toDto(personalNacidIdEntity);
    }

    @Override
    public PersonalNacidIdDTO generateAndSave(String userGenerated) {
        PersonalNacidIdDTO personalNacidId = new PersonalNacidIdDTO();
        personalNacidId.setUserGenerated(userGenerated);

        String value;
        do {
            value = RandomNumberUtils.generateEightDigitsRandomString();
        } while (Objects.nonNull(selectByValue(value)));

        personalNacidId.setValue(value);
        personalNacidId.setDateGenerated(LocalDateTime.now());
        return personalNacidIdMapper.toDto(personalNacidIdRepository.save(personalNacidIdMapper.toEntity(personalNacidId)));
    }

}
