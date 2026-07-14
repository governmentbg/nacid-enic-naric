package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vw_school_grade", schema = "nomenclatures")
@Cacheable(value = false)
public class VSchoolGradeEntity implements Serializable {
    @Id
    @Column(name = "school_grade")
    private String schoolGrade;
}
