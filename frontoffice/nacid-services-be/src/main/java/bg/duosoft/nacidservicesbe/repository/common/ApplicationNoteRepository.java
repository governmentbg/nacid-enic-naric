package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationNoteRepository extends JpaRepository<ApplicationNoteEntity, Integer> {

    List<ApplicationNoteEntity> getAllByApplicationIdOrderByDateCreatedDesc(Integer id);
}
