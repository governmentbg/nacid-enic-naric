package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationCertificateRepository extends BaseRepository<ApplicationCertificatesEntity, Integer> {
    @Query("SELECT a from ApplicationCertificatesEntity a where a.application.id = :apnId and a.certificateStatus = 'P'")
    List<ApplicationCertificatesEntity> selectPublishedCertificatesByApnId(@Param("apnId") Integer apnId);
}
