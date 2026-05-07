package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.LanguageSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LanguageEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LanguageFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends NomenclatureBaseRepository<String, LanguageEntity, LanguageFilterDTO>, LanguageSearchRepository{

    @Query(value = "SELECT l.* FROM nomenclatures.language l JOIN nomenclatures.cfg_language_to_app_type c ON l.code = c.lae_code WHERE c.ate_code=:appType and c.ase_code=:appSubType", nativeQuery = true)
    List<LanguageEntity> selectByApplicationTypeSubtype(@Param("appType") String appType, @Param("appSubType") String appSubType);

}
