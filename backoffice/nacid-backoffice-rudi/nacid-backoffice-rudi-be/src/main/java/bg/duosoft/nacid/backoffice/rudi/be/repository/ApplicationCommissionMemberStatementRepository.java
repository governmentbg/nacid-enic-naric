package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberStatementEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationCommissionMemberStatementRepository extends BaseRepository<ApplicationCommissionMemberStatementEntity, Integer> {
    @Query("SELECT r from ApplicationCommissionMemberStatementEntity r where r.application.id = :applicationId order by r.id desc")
    List<ApplicationCommissionMemberStatementEntity> selectByApplicationId(Integer applicationId);
}
