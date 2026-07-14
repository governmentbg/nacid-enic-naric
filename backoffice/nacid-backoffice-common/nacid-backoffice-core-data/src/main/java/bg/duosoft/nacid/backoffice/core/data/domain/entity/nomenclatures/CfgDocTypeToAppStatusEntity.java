package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 05.04.2023
 * Time: 12:52
 */
@Table(name = "cfg_doc_type_to_app_status", schema = "nomenclatures")
@Entity
@Data
public class CfgDocTypeToAppStatusEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dte_id", referencedColumnName = "id")
    private DocumentTypeEntity documentType;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName = "code")
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'APPLICATION_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity status;
}
