package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "national_qualifications_framework", schema = "nomenclatures")
public class NationalQualificationsFrameworkEntity implements IntegerKeyNomenclatureEntityBase {
    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(generator = "nqf-sequence-generator")
//    @GenericGenerator(strategy = "bg.duosoft.nacid.backoffice.core.data.domain.CustomSequenceGenerator", name = "nqf-sequence-generator", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "nomenclatures.national_qualifications_framework_id_seq")})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

}