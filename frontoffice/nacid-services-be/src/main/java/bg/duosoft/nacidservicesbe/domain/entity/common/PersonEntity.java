package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CivilIdTypeEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkSettlementEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 15:56
 */
@Entity
@Table(name = "person", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "civil_id")
    private String civilId;

    @ManyToOne
    @JoinColumn(name = "civil_id_type")
    private CivilIdTypeEntity civilIdType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'FOREIGN_IDENTIFIER_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "foreign_identifier_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity foreignIdType;

    @ManyToOne
    @JoinColumn(name = "foreign_identifier_country", referencedColumnName = "code")
    private CountryEntity foreignIdCountry;

    @Column(name = "legal_type")
    private String legalTypeCode;

    @Column(name = "legal_nature_type")
    private String legalNatureTypeCode;

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

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'HUMANITARIAN_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "humanitarian_status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity humanitarianStatus;

    @Column(name = "title")
    private String title;

    public String getFullName(){
        if(StringUtils.hasText(firstName)){
            return String.format("%s%s%s", firstName, StringUtils.hasText(secondName) ? " "+secondName: "", StringUtils.hasText(lastName) ? " "+lastName: "");
        } else if(StringUtils.hasText(legalName)){
            return legalName;
        }
        return null;
    }
}
