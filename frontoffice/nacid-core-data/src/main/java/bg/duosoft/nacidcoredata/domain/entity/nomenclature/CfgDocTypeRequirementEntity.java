package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:26
 */
@Entity
@Table(name = "cfg_doc_type_requirement", schema = "nomenclatures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CfgDocTypeRequirementEntity implements Serializable {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dte_id")
    private DocTypeEntity docType;

    @Column(name = "cte_code")
    private String copyTypeCode;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "ase_code")
    private String applicationSubtypeCode;

    @Column(name = "requirement_key")
    private String requirementKey;

    @Column(name = "requirement_expression")
    private String requirementExpression;

    @Column(name = "template_url")
    private String templateUrl;
}
