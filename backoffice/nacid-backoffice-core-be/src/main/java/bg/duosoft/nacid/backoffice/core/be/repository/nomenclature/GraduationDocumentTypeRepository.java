package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.GraduationDocumentTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GraduationDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.GraduationDocumentTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraduationDocumentTypeRepository extends NomenclatureBaseRepository<Integer, GraduationDocumentTypeEntity, GraduationDocumentTypeFilterDTO>, GraduationDocumentTypeSearchRepository {

    @Query("SELECT r from GraduationDocumentTypeEntity r inner join r.configs c where c.pk.educationType = :educationType and c.pk.countryCode = :countryCode")
    List<GraduationDocumentTypeEntity> selectByCountryAndEducation(String countryCode, String educationType);

    @Query("SELECT distinct r from GraduationDocumentTypeEntity r inner join r.configs c where c.pk.countryCode = :countryCode")
    List<GraduationDocumentTypeEntity> selectByCountry(String countryCode);

    @Query("SELECT r from GraduationDocumentTypeEntity r where r.name = :name")
    List<GraduationDocumentTypeEntity> selectByName(String name);

}
