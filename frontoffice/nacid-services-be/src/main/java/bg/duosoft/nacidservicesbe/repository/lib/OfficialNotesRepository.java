package bg.duosoft.nacidservicesbe.repository.lib;

import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:13
 */
public interface OfficialNotesRepository extends JpaRepository<OfficialNoteEntity, Integer> {
}
