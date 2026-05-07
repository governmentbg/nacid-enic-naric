package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:26
 */
@Getter
@Setter
@Embeddable
public class CfgSarAppStatusPK implements Serializable {
    @Column(name = "sar_ate")
    private String sarAte;

    @Column(name = "status_code")
    private String statusCode;

}
