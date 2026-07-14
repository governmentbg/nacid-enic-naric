package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.IntegerKeyNomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "training_institution", schema = "rudi")
public class TrainingInstitutionEntity implements IntegerKeyNomenclatureEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "active")
    private Integer active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @Column(name = "web_site")
    private String webSite;

    @ManyToMany
    @JoinTable(
            schema = "rudi",
            name = "training_institution_university",
            joinColumns = @JoinColumn(name = "tin_id"),
            inverseJoinColumns = @JoinColumn(name = "uny_id"))
    Set<UniversityEntity> universities;

}
