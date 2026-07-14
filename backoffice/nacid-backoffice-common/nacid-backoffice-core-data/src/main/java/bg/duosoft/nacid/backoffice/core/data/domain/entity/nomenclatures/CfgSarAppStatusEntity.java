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
@Table(name = "cfg_sar_app_status", schema = "nomenclatures")
public class CfgSarAppStatusEntity implements Serializable {
    @EmbeddedId
    private CfgSarAppStatusPK pk;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'SAR_APPLICATION_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "sar_ate", referencedColumnName="code", updatable = false, insertable = false))
    })
    private ReferenceDataEntity sarApplicationType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'APPLICATION_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName="code", updatable = false, insertable = false))
    })
    private ReferenceDataEntity status;
    @Column(name = "positive_flag")
    private Integer positiveFlag;

}
