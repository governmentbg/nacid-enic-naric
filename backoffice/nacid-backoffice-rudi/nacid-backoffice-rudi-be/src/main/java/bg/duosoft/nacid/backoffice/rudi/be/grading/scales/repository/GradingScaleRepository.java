package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradingScaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradingScaleRepository extends JpaRepository<RudiGradingScaleEntity, Integer> {
    @Query("""
                SELECT gs
                FROM RudiGradingScaleEntity gs
                WHERE gs.country.code = :countryCode
                AND ( :year IS NOT NULL
                  AND (gs.startYear IS NULL OR gs.startYear <= :year)
                  AND (gs.endYear IS NULL OR gs.endYear >= :year)
                )
                ORDER BY gs.id
            """)
    List<RudiGradingScaleEntity> getGradingScalesByCountryCodeAndYear(
            @Param("countryCode") String countryCode,
            @Param("year") Integer year
    );
}
