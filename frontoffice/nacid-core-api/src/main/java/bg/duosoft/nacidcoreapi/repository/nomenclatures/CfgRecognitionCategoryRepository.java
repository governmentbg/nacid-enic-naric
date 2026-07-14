package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgRecognitionCategoryEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgRecognitionCategoryIdEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 13:11
 */
public interface CfgRecognitionCategoryRepository extends BaseRepository<CfgRecognitionCategoryEntity, CfgRecognitionCategoryIdEntity> {

    List<CfgRecognitionCategoryEntity> findAllByOrderByRecognitionCategory_IndexAsc();

}