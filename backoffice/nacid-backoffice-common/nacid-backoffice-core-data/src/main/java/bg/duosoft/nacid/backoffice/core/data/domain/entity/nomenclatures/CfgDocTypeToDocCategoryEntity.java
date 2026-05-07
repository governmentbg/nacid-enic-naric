package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
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
@Table(name = "cfg_doc_type_to_doc_category", schema = "nomenclatures")
public class CfgDocTypeToDocCategoryEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dte_id", referencedColumnName = "id")
    private DocumentTypeEntity documentType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'DOC_CATEGORY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "dcy_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity documentCategory;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName = "code")
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName = "code")
    private ApplicationSubtypeEntity applicationSubtype;

    @Column(name = "condition")
    private String condition;

    @Column(name = "additional_description")
    private String additionalDescription;

    @Column(name = "template")
    private String template;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'DOCUMENT_FINALIZATION_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "finalization_type", referencedColumnName="code"))
    })
    private ReferenceDataEntity finalizationType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'ATTACHMENT_VISIBILITY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "default_attachment_visibility", referencedColumnName="code"))
    })
    private ReferenceDataEntity defaultAttachmentVisibility;
}
