package bg.duosoft.nacidservicesbe.repository.base;

import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 14:18
 */
@NoRepositoryBean
public interface FullApplicationRepositoryBase <E extends FullApplicationEntityBase> extends JpaRepository<E, Integer> {

    Optional<E> findByApplication_EntryNumberAndApplicationEntryDateAndApplication_AccessCode(String entryNumber, LocalDate entryDate, String accessCode);
}
