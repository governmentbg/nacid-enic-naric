package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CivilIdTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "person", schema = "common")
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_name")
    private String otherName;

    @Column(name = "latin_first_name")
    private String latinFirstName;

    @Column(name = "latin_middle_name")
    private String latinMiddleName;

    @Column(name = "latin_last_name")
    private String latinLastName;

    @Column(name = "latin_other_name")
    private String latinOtherName;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "civil_id")
    private String civilId;

    @ManyToOne
    @JoinColumn(name = "civil_id_type", referencedColumnName = "code")
    private CivilIdTypeEntity civilIdType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'FOREIGN_IDENTIFIER_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "foreign_identifier_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity foreignIdentifierType;

    @ManyToOne
    @JoinColumn(name = "foreign_identifier_country", referencedColumnName = "code")
    private CountryEntity foreignIdentifierCountry;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'LEGAL_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "legal_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity legalType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'LEGAL_NATURE_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "legal_nature_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity legalNatureType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'HUMANITARIAN_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "humanitarian_status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity humanitarianStatus;

    @ManyToOne
    @JoinColumn(name = "origin_country", referencedColumnName = "code")
    private CountryEntity originCountry;

    @Column(name = "origin_city")
    private String originCity;

    @ManyToOne
    @JoinColumn(name = "origin_set_code", referencedColumnName = "code")
    private EkSettlementEntity originSettlement;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "citizenship_id", referencedColumnName = "code")
    private CountryEntity citizenship;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "honorific")
    private String honorific;

    @Column(name = "active")
    private Integer isActive;

}
