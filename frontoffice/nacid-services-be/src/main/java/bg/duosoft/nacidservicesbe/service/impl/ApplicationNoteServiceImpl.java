package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationNoteDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationNoteEntity;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationNoteMapper;
import bg.duosoft.nacidservicesbe.repository.common.ApplicationNoteRepository;
import bg.duosoft.nacidservicesbe.service.ApplicationNoteService;
import bg.duosoft.nacidservicesbe.validation.common.note.ApplicationNoteValidator;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationNoteServiceImpl implements ApplicationNoteService {

    private final ApplicationNoteMapper mapper;
    private final ApplicationNoteValidator validator;
    private final ApplicationNoteRepository repository;

    @Override
    public List<ApplicationNoteDTO> selectAllByApplication(Integer id) {
        return mapper.toDtoList(repository.getAllByApplicationIdOrderByDateCreatedDesc(id));
    }

    @Override
    public ApplicationNoteDTO selectById(Integer id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public void delete(Integer id) {
        if (Objects.isNull(id)) {
            return;
        }

        repository.deleteById(id);
    }

    @Override
    public ApplicationNoteDTO create(ApplicationNoteDTO applicationNote) {
        BadRequestValidator.validateRequest(validator, applicationNote);

        applicationNote.setDateCreated(LocalDateTime.now());
        applicationNote.setUserCreated(SecurityUtils.getUsername());
        return save(applicationNote);
    }

    @Override
    public ApplicationNoteDTO update(ApplicationNoteDTO applicationNote) {
        ApplicationNoteDTO dbApplicationNote = ResponseUtils.notFoundCheck(Objects.nonNull(applicationNote.getId()) ? selectById(applicationNote.getId()) : null);
        BadRequestValidator.validateRequest(validator, applicationNote);

        dbApplicationNote.setDateUpdated(LocalDateTime.now());
        dbApplicationNote.setUserUpdated(SecurityUtils.getUsername());
        dbApplicationNote.setNoteText(applicationNote.getNoteText());
        return save(dbApplicationNote);
    }

    private ApplicationNoteDTO save(ApplicationNoteDTO applicationNote) {
        ApplicationNoteEntity entity = mapper.toEntity(applicationNote);
        return mapper.toDto(repository.save(entity));
    }
}
