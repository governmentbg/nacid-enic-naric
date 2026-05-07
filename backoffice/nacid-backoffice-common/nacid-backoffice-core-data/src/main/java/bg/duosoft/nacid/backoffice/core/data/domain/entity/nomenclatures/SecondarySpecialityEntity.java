package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "secondary_speciality", schema = "nomenclatures")
public class SecondarySpecialityEntity implements IntegerKeyNomenclatureEntityBase {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "professional_qualification_id", referencedColumnName = "id")
    private SecondaryProfessionalQualificationEntity qualification;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'QUALIFICATION_DEGREE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "qualification_degree", referencedColumnName = "code"))
    })
    private ReferenceDataEntity qualificationDegree;

}
