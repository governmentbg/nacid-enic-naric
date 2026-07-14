package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 02.11.2022
 * Time: 11:41
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "cfg_report_field", schema = "nomenclatures")
public class CfgReportFieldEntity implements Serializable {
    @Id
    @Column(name = "code")
    private String id;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "sql_code", referencedColumnName = "code")
    private CfgReportSqlEntity sql;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'REPORT_FIELD_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "field_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity fieldType;

}
