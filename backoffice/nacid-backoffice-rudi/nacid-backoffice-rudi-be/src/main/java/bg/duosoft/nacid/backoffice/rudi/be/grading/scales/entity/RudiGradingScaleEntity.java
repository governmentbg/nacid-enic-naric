package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.enums.ScaleTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grading_scale", schema = "secondary")
public class RudiGradingScaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "scale_name")
    private String scaleName;

    @Column(name = "scale_type")
    @Enumerated(EnumType.STRING)
    private ScaleTypeEnum scaleType;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code", nullable = false)
    private CountriesEntity country;

}
