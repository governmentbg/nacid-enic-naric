package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CivilIdTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Cacheable(value = false)
@Table(name = "applicant_diploma_names", schema = "common")
public class ApplicantDiplomaNamesEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "civil_id")
    private String civilId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @ManyToOne
    @JoinColumn(name = "civil_id_type", referencedColumnName = "code")
    private CivilIdTypeEntity civilIdType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'FOREIGN_IDENTIFIER_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "foreign_identifier_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity foreignIdentifierType;

    @ManyToOne
    @JoinColumn(name = "foreign_identifier_country", referencedColumnName = "code")
    private CountryEntity foreignIdentifierCountry;

}
