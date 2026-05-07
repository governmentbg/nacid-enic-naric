package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "cfg_copy_type_to_app_type", schema = "nomenclatures")
public class CfgCopyTypeEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'COPY_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "copy_type_code", referencedColumnName="code", updatable = false, insertable = false))
    })
    private ReferenceDataEntity copyType;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "ase_code")
    private String applicationSubtypeCode;

    @Column(name = "show_expression")
    private String showExpression;
}
