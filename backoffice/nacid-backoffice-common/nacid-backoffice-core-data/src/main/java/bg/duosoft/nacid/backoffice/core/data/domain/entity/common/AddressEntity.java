package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
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
@Table(name = "address", schema = "common")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @ManyToOne
    @JoinColumn(name = "coy_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "city_name")
    private String city;

    @Column(name = "post_box")
    private String postBox;

    @ManyToOne
    @JoinColumn(name = "set_code", referencedColumnName = "code")
    private EkSettlementEntity settlement;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'ADDRESS_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "ate_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity addressType;

}
