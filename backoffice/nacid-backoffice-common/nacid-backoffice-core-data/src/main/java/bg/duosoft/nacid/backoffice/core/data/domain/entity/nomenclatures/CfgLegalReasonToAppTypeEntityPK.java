package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 27.06.2023
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgLegalReasonToAppTypeEntityPK implements Serializable {
    @Column(name = "lrn_id", nullable = false)
    private Integer lrnId;
    @Column(name = "ate_code", nullable = false)
    private String ateCode;
    @Column(name = "ase_code", nullable = false)
    private String aseCode;
}
