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
 * Date: 02.12.2022
 * Time: 16:24
 */
@Entity
@Table(name = "cfg_edu_level_to_app_type", schema = "nomenclatures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CfgEduLevelEntity implements Serializable {

    @EmbeddedId
    private CfgEduLevelEntityPK id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'EDUCATION_LEVEL'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "ell_code", referencedColumnName="code", updatable = false, insertable = false))
    })
    private ReferenceDataEntity eduLevel;
}
