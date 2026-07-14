package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "grade_equivalence", schema = "secondary")
public class GradeEquivalenceEntity implements NomenclatureEntityBase<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bulgarian_grade")
    private BigDecimal bulgarianGrade;

    @Column(name = "bulgarian_grade_text")
    private String name;

    @Column(name = "active")
    private Integer active;
}
