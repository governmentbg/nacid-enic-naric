package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationNoteDTO;

import java.util.List;

public interface ApplicationNoteService {
    List<ApplicationNoteDTO> selectAllByApplication(Integer id);
    ApplicationNoteDTO selectById(Integer id);
    void delete(Integer id);
    ApplicationNoteDTO create(ApplicationNoteDTO applicationNote);
    ApplicationNoteDTO update(ApplicationNoteDTO applicationNote);
}
