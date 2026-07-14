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
 * Date: 01.06.2023
 * Time: 13:02
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgRecognitionCategoryIdEntity implements Serializable {

    @Column(name = "rcy_code", nullable = false)
    private String recognitionCategoryCode;

    @Column(name = "ate_code", nullable = false)
    private String applicationTypeCode;

    @Column(name = "ase_code", nullable = false)
    private String applicationSubtypeCode;
}
