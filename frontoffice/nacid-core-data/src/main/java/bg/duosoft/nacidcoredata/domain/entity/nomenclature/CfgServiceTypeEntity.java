package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 11:20
 */
@Entity
@Table(name = "cfg_service_type", schema = "nomenclatures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CfgServiceTypeEntity implements Serializable {

    @Id
    private Integer id;

    @Column(name = "ate_code", nullable = false)
    private String applicationTypeCode;

    @Column(name = "ase_code", nullable = false)
    private String applicationSubtypeCode;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'SERVICE_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "service_type_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity serviceType;

    @Column(name = "execution_days")
    private Integer executionDays;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'EXECUTION_DAYS_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "execution_days_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity executionDaysType;
}
