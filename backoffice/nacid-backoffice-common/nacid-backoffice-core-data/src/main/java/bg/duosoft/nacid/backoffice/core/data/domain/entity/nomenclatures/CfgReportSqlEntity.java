package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 02.11.2022
 * Time: 11:37
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Cacheable(value = false)
@EqualsAndHashCode(exclude = "fields")
@Table(name = "cfg_report_sql", schema = "nomenclatures")
public class CfgReportSqlEntity implements Serializable {
    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sql_expression", nullable = false)
    private String sqlExpression;

    @Column(name = "many_rows_flag", nullable = false)
    private Integer manyRowsFlag;

    @Column(name = "group_flag", nullable = false)
    private Integer groupFlag;

    @Column(name = "start_text")
    private String startText;

    @Column(name = "end_text")
    private String endText;

    @Column(name = "separator_text")
    private String separatorText;

    @OneToMany(mappedBy = "sql", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id asc")
    private List<CfgReportFieldEntity> fields;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
}
