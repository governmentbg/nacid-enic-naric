package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.CountriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountriesEntity, String> {
    @Query("SELECT distinct c FROM CountriesEntity AS c JOIN FETCH c.referencedCountry WHERE c.active=1 ORDER BY c.code")
    List<CountriesEntity> getActiveCountries();

    @Query("SELECT c FROM CountriesEntity c WHERE c.code=:code")
    CountriesEntity getCountryByCode(@Param("code") String code);
}