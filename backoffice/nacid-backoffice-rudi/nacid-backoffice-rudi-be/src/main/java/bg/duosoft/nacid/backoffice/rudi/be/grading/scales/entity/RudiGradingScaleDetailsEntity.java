package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grading_scale_details", schema = "secondary")
public class RudiGradingScaleDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "symbol_values")
    private String symbolValues;

    @Column(name = "min_value")
    private Double minValue;

    @Column(name = "max_value")
    private Double maxValue;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "grading_scale_id", nullable = false)
    private RudiGradingScaleEntity gradingScale;

    @ManyToOne
    @JoinColumn(name = "grade_equivalences_id", nullable = false)
    private RudiGradeEquivalenceEntity gradeEquivalence;
}
