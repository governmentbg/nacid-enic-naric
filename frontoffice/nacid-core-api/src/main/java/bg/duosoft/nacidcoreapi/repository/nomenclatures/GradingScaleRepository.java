package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GradingScaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradingScaleRepository extends JpaRepository<GradingScaleEntity, Integer> {
    @Query(" SELECT gs FROM GradingScaleEntity gs WHERE :countryCode IS NOT NULL AND gs.active>0 AND (gs.country.id = :countryCode or gs.country is null and gs.alternateKey is null) AND ( :year IS NOT NULL AND (gs.startYear IS NULL OR gs.startYear <= :year) AND (gs.endYear IS NULL OR gs.endYear >= :year)) ORDER BY gs.id")
    List<GradingScaleEntity> getGradingScalesByCountryCodeAndYear(@Param("countryCode") String countryCode, @Param("year") Integer year);

    @Query(" SELECT gs FROM GradingScaleEntity gs WHERE :alternateKey IS NOT NULL AND gs.active>0 AND gs.alternateKey = :alternateKey AND ( :year IS NOT NULL AND (gs.startYear IS NULL OR gs.startYear <= :year) AND (gs.endYear IS NULL OR gs.endYear >= :year)) ORDER BY gs.id")
    List<GradingScaleEntity> getGradingScalesByAlternateKeyAndYear(@Param("alternateKey") String alternateKey, @Param("year") Integer year);

    @Query(" SELECT distinct gs.country FROM GradingScaleEntity gs")
    List<CountryEntity> getGradingScaleCountries();
}
