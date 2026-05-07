package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 12:00
 */
@Entity
@Table(name = "regprof_profession_experience_document_dates", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofExperienceDocumentDateEntity implements Serializable {

    @EmbeddedId
    private RegprofExperienceDocumentDateIdEntity id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ped_idx", referencedColumnName = "idx", nullable = false),
            @JoinColumn(name = "rte_id", referencedColumnName = "rte_id", nullable = false),
    })
    @MapsId("experienceDocumentId")
    private RegprofExperienceDocumentEntity experienceDocument;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'WORKDAY_DURATION'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "workday_duration", referencedColumnName="code"))
    })
    private ReferenceDataEntity workdayDuration;
}
