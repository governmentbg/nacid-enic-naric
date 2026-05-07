package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "university", schema = "rudi")
public class UniversityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "bg_name")
    private String bgName;

    @Column(name = "org_name")
    private String orgName;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "active")
    private Integer active;

    @Column(name = "url_diploma_register")
    private String urlDiplomaRegister;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacultyEntity> faculties;
}
