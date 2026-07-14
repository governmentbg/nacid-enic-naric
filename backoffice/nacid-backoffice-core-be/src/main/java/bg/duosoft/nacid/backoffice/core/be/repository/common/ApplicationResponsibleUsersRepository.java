package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationResponsibleUsersEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationResponsibleUsersRepository extends BaseRepository<ApplicationResponsibleUsersEntity, Integer> {
    @Query("SELECT r from ApplicationResponsibleUsersEntity r where r.application.id = :applicationId order by r.id desc")
    List<ApplicationResponsibleUsersEntity> selectByApplicationId(Integer applicationId);

    @Query("SELECT r from ApplicationResponsibleUsersEntity r where r.application.id = :applicationId and r.dateTo is null ")
    ApplicationResponsibleUsersEntity selectMainResponsibleUserByApplicationId(Integer applicationId);
}
