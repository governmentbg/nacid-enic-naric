package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grade_equivalence", schema = "secondary")
public class RudiGradeEquivalenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bulgarian_grade")
    private Double bulgarianGrade;

    @Column(name = "bulgarian_grade_text")
    private String bulgarianGradeText;
}
