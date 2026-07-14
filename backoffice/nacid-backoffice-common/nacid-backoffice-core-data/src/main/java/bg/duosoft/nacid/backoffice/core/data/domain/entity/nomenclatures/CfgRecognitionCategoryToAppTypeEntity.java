package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 30.05.2023
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "cfg_recognition_category_to_app_type", schema = "nomenclatures")
public class CfgRecognitionCategoryToAppTypeEntity implements Serializable {
    @EmbeddedId
    private CfgRecognitionCategoryToAppTypeEntityPK pk;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName="code", updatable = false, insertable = false)
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName="code", updatable = false, insertable = false)
    private ApplicationSubtypeEntity applicationSubtype;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'RECOGNITION_CATEGORY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rcy_code", referencedColumnName="code", insertable = false, updatable = false))
    })
    private ReferenceDataEntity recognitionCategory;
}
