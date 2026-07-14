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
 * Date: 15.09.2022
 * Time: 17:39
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgGraduationWayToAppTypeEntityPK implements Serializable {
    @Column(name = "gwy_code", nullable = false)
    private String gwyCode;
    @Column(name = "ate_code", nullable = false)
    private String ateCode;
    @Column(name = "ase_code", nullable = false)
    private String aseCode;
}
