package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkSettlementEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 15:27
 */
@Entity
@Table(name = "address", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private EkSettlementEntity citySettlement;

    @Column(name = "ate_code")
    private String addressTypeCode;
}
