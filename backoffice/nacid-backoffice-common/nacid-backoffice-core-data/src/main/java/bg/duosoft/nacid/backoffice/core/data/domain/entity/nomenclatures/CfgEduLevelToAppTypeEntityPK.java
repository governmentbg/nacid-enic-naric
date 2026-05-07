package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 17:39
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgEduLevelToAppTypeEntityPK implements Serializable {
    @Column(name = "ell_code", nullable = false)
    private String ellCode;
    @Column(name = "ate_code", nullable = false)
    private String ateCode;
    @Column(name = "ase_code", nullable = false)
    private String aseCode;
}
