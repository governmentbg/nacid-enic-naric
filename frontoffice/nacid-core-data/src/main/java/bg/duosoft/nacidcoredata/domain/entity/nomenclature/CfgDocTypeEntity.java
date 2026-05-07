package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 18:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "cfg_doc_type_to_app_type", schema = "nomenclatures")
public class CfgDocTypeEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dte_id")
    private DocTypeEntity docType;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "ase_code")
    private String applicationSubtypeCode;

    @Column(name = "show_expression")
    private String showExpression;
}
