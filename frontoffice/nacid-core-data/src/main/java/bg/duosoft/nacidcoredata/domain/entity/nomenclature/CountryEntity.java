package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "country", schema = "nomenclatures")
@EqualsAndHashCode
public class CountryEntity implements Serializable, NomenclatureEntityBase<String> {
    @Id
    @Column(name = "code", nullable = false, length = 2)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "native_name")
    private String nativeName;

    @Column(name = "active", nullable = false)
    private Integer active;

}