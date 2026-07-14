package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "country", schema = "secondary")
public class CountriesEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "active", nullable = false)
    private Integer active;

    @OneToOne
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    private CountryEntity referencedCountry;
}
