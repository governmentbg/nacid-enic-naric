package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "secondary_professional_qualification", schema = "nomenclatures")
public class SecondaryProfessionalQualificationEntity implements IntegerKeyNomenclatureEntityBase {
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
    @JoinColumn(name = "profession_group_id", referencedColumnName = "id")
    private SecondaryProfessionGroupEntity professionGroup;

}
