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
 * Date: 18.08.2022
 * Time: 16:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "cfg_doc_type_to_abdocs_Config", schema = "nomenclatures")
public class CfgDocTypeToAbdocsConfigEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dte_id", referencedColumnName = "id")
    private DocumentTypeEntity documentType;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName = "code")
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName = "code")
    private ApplicationSubtypeEntity applicationSubtype;

    @Column(name = "abdocs_doc_type_id")
    private Integer abdocsDocTypeId;

    @Column(name = "abdocs_autoinsert_flag")
    private Integer abdocsAutoInsertFlag;

    @Column(name = "abdocs_task_result")
    private String abdocsTaskResult;

    @Column(name = "abdocs_task_user")
    private String abdocsTaskUser;

    @Column(name = "abdocs_doc_from")
    private String abdocsDocFrom;

    @Column(name = "abdocs_doc_editor")
    private String abdocsDocEditor;
}
