package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grade_equivalence", schema = "services")
public class GradeEquivalenceEntity implements NomenclatureEntityBase<Integer> {
    @Id
    private Integer id;

    @Column(name = "bulgarian_grade")
    private Double bulgarianGrade;

    @Column(name = "bulgarian_grade_text")
    private String name;

    @Column(name = "active")
    private Integer active;
}