package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedDetailsEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRecognizedDetailsRepository extends BaseRepository<ApplicationRecognizedDetailsEntity, Integer> {

    @Query("SELECT r.recognizedQualification from ApplicationRecognizedDetailsEntity r group by r.recognizedQualification order by r.recognizedQualification")
    List<String> selectAllDistinctQualifications();
}
