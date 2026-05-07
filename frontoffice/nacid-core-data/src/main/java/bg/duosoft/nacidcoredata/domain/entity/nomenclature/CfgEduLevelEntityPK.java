package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 16:24
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgEduLevelEntityPK implements Serializable {

    @Column(name = "ell_code", nullable = false)
    private String eduLevelCode;

    @Column(name = "ate_code", nullable = false)
    private String applicationTypeCode;

    @Column(name = "ase_code", nullable = false)
    private String applicationSubtypeCode;
}
