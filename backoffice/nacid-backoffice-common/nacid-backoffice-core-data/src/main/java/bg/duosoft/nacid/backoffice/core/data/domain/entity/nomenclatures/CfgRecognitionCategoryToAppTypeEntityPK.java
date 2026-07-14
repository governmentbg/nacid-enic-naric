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
 * Date: 30.05.2023
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgRecognitionCategoryToAppTypeEntityPK implements Serializable {
    @Column(name = "rcy_code", nullable = false)
    private String rcyCode;
    @Column(name = "ate_code", nullable = false)
    private String ateCode;
    @Column(name = "ase_code", nullable = false)
    private String aseCode;
}
