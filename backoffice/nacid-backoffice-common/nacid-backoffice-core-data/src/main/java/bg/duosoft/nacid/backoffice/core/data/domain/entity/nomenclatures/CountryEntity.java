package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "country", schema = "nomenclatures")
public class CountryEntity implements Serializable, StringKeyNomenclatureEntityBase {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "citizenship_name", nullable = false)
    private String citizenshipName;

    @Column(name = "active", nullable = false)
    private Integer active;

}