package bg.duosoft.nacidservicesbe.repository.lib;

import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 11:53
 */
public interface InquiryRepository extends JpaRepository<InquiryEntity, Integer> {
}
