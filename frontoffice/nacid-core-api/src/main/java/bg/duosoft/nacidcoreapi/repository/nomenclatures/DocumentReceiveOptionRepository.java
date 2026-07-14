package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocumentReceiveOptionEntity;
import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentReceiveOptionRepository extends BaseRepository<DocumentReceiveOptionEntity, String> {

    @Query("SELECT c FROM DocumentReceiveOptionEntity c WHERE c.optionKind.id  = ?1 AND c.active = 1 order by c.index")
    List<DocumentReceiveOptionEntity> selectByKind(String kind);
}
