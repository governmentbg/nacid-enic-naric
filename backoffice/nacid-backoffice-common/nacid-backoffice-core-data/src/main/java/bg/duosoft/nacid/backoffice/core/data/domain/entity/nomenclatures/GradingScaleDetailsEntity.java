package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "grading_scale_details", schema = "secondary")
public class GradingScaleDetailsEntity implements Serializable {

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

    @Column(name = "index")
    private Integer index;

    @OneToOne
    @JoinColumn(name = "grading_scale_id", nullable = false)
    private GradingScaleEntity gradingScale;

    @ManyToOne
    @JoinColumn(name = "grade_equivalences_id", nullable = false)
    private GradeEquivalenceEntity gradeEquivalence;
}
