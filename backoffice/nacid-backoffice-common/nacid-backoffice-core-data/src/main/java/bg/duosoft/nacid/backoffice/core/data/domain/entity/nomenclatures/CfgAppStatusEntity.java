package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "cfg_app_status", schema = "nomenclatures")
public class CfgAppStatusEntity implements Serializable {
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
            @JoinColumnOrFormula(formula=@JoinFormula(value="'APPLICATION_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity status;
    @Column(name = "legal_flag")
    private Integer legalFlag;

    @Column(name = "commission_flag")
    private Integer commissionFlag;
    private Integer active;

    @Column(name = "initial_status_flag")
    private Integer initialStatusFlag;

    @Column(name = "execution_suspended_flag")
    private Integer executionSuspendedFlag;

}
