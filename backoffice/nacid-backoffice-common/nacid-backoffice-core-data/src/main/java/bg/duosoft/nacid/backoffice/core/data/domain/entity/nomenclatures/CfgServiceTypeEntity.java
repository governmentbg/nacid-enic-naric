package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

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
@Table(name = "cfg_service_type", schema = "nomenclatures")
public class CfgServiceTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName="code")
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName="code")
    private ApplicationSubtypeEntity applicationSubtype;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'SERVICE_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "service_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity serviceType;

    @Column(name = "execution_days")
    private Integer executionDays;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'EXECUTION_DAYS_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "execution_days_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity executionDaysType;

}
