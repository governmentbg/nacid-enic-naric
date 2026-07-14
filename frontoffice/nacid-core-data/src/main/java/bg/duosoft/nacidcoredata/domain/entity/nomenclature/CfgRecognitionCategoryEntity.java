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
 * Date: 01.06.2023
 * Time: 13:08
 */
@Entity
@Table(name = "cfg_recognition_category_to_app_type", schema = "nomenclatures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CfgRecognitionCategoryEntity implements Serializable {

    @EmbeddedId
    private CfgRecognitionCategoryIdEntity id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'RECOGNITION_CATEGORY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rcy_code", referencedColumnName="code", updatable = false, insertable = false))
    })
    private ReferenceDataEntity recognitionCategory;
}
