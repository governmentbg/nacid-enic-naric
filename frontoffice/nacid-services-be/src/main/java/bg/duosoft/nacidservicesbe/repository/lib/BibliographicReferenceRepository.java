package bg.duosoft.nacidservicesbe.repository.lib;

import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 14:09
 */
public interface BibliographicReferenceRepository extends JpaRepository<BibliographicReferenceEntity, Integer> {
}
