package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "prof_group", schema = "nomenclatures")
public class ProfGroupEntity implements IntegerKeyNomenclatureEntityBase {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "pgp-sequence-generator")
//    @GenericGenerator(strategy = "bg.duosoft.nacid.backoffice.core.data.domain.CustomSequenceGenerator", name = "pgp-sequence-generator", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "nomenclatures.prof_group_id_seq")})
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'EDUCATION_AREA'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "edu_area", referencedColumnName="code"))
    })
    private ReferenceDataEntity educationArea;

    public ProfGroupEntity(Integer id) {
        this.id = id;
    }
}