package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grading_scale", schema = "secondary")
public class GradingScaleEntity implements NomenclatureEntityBase<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "scale_name")
    private String name;

    @Column(name = "active")
    private Integer active;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'GRADING_SCALE_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "scale_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity scaleType;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "alternate_key")
    private String alternateKey;

}
