package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 15:37
 */
@Entity
@Table(name = "applicant_diploma_names", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicantDiplomaNamesEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer applicationId;

    @OneToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id")
    @MapsId
    private ApplicationEntity application;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "civil_id")
    private String civilId;

    @Column(name = "civil_id_type")
    private String civilIdTypeCode;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'FOREIGN_IDENTIFIER_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "foreign_identifier_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity foreignIdType;

    @ManyToOne
    @JoinColumn(name = "foreign_identifier_country", referencedColumnName = "code")
    private CountryEntity foreignIdCountry;
}
