package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.IntegerKeyNomenclatureEntityBase;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "competent_institution", schema = "rudi")
public class CompetentInstitutionEntity implements IntegerKeyNomenclatureEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "coy_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "url")
    private String url;

    @Column(name = "notes")
    private String notes;

    @Column(name = "active")
    private Integer active;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

}
