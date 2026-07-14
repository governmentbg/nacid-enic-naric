package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ApplicationCommissionMemberRepository extends BaseRepository<ApplicationCommissionMemberEntity, Integer> {
    @Query("SELECT r from ApplicationCommissionMemberEntity r where r.application.id = :applicationId order by r.id asc")
    List<ApplicationCommissionMemberEntity> selectByApplicationId(Integer applicationId);

}
