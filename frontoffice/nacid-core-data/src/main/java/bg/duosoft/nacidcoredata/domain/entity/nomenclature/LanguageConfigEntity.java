package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 17:39
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "cfg_language_to_app_type", schema = "nomenclatures")
public class LanguageConfigEntity implements Serializable {

    @Id
    private Integer id;

    @Column(name = "lae_code")
    private String languageCode;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "ase_code")
    private String applicationSubtypeCode;
}
