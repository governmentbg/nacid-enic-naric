package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "civil_id_type", schema = "nomenclatures")
public class CivilIdTypeEntity implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'LEGAL_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "legal_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity legalType;


    public CivilIdTypeEntity(String id) {
        this.id = id;
    }
}