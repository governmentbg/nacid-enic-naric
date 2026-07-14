package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ek_municipality", schema = "nomenclatures")
@Cacheable(value = false)
public class EkMunicipalityEntity implements Serializable, NomenclatureEntityBase<String> {

    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "districtcode")
    private String districtcode;

    @Column(name = "code2")
    private String code2;

    @Column(name = "mainsettlementcode")
    private String mainsettlementcode;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "isactive")
    private Integer active;

    @Column(name = "version")
    private Integer version;

    @Column(name = "nameen")
    private String nameen;
}