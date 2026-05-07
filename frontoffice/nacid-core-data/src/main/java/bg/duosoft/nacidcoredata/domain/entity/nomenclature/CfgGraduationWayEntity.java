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
 * Time: 13:43
 */
@Entity
@Table(name = "cfg_graduation_way_to_app_type", schema = "nomenclatures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CfgGraduationWayEntity implements Serializable {

    @EmbeddedId
    private CfgGraduationWayEntityPK id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'GRADUATION_WAY'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "gwy_code", referencedColumnName="code", updatable = false, insertable = false))
    })
    private ReferenceDataEntity graduationWay;
}
