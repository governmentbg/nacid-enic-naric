package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "european_qualifications_framework", schema = "nomenclatures")
public class EuropeanQualificationsFrameworkEntity implements IntegerKeyNomenclatureEntityBase {
    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(generator = "eqf-sequence-generator")
//    @GenericGenerator(strategy = "bg.duosoft.nacid.backoffice.core.data.domain.CustomSequenceGenerator", name = "eqf-sequence-generator", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "nomenclatures.european_qualifications_framework_id_seq")})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

}