package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "application_type", schema = "nomenclatures")
public class ApplicationTypeEntity implements Serializable, StringKeyNomenclatureEntityBase {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

}