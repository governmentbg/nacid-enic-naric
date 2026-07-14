package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "language", schema = "nomenclatures")
public class LanguageEntity  implements Serializable, NomenclatureEntityBase<String> {

    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Integer active;

    @OneToMany
    @JoinColumn(name = "lae_code", referencedColumnName = "code")
    private List<LanguageConfigEntity> configs;
}
