package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "dictionary", schema = "nomenclatures")
public class DictionaryEntity implements StringKeyNomenclatureEntityBase {

    @Id
    @Column(name = "code", nullable = false, length = 100)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;
}
