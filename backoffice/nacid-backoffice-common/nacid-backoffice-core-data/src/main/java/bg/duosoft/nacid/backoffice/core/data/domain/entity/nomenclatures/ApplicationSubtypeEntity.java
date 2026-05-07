package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "application_subtype", schema = "nomenclatures")
public class ApplicationSubtypeEntity implements Serializable, StringKeyNomenclatureEntityBase {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;
    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName = "code")
    private ApplicationTypeEntity applicationType;
}
