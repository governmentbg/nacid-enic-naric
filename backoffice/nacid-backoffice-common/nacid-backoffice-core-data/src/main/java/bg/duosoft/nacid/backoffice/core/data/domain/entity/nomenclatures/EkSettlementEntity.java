package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ek_settlement", schema = "nomenclatures")
@Cacheable(value = false)
public class EkSettlementEntity implements Serializable, NomenclatureEntityBase<String> {

    @Id
    @Column(name = "code")
    private String id;

    @ManyToOne
    @JoinColumn(name = "municipalitycode", referencedColumnName = "code")
    private EkMunicipalityEntity municipalitycode;

    @ManyToOne
    @JoinColumn(name = "districtcode", referencedColumnName = "code")
    private EkDistrictEntity districtcode;

    @Column(name = "municipalitycode2")
    private String municipalitycode2;

    @Column(name = "districtcode2")
    private String districtcode2;

    @Column(name = "name")
    private String name;

    @Column(name = "typename")
    private String typename;

    @Column(name = "settlementname")
    private String settlementname;

    @Column(name = "typecode")
    private String typecode;

    @Column(name = "mayoraltycode")
    private String mayoraltycode;

    @Column(name = "category")
    private String category;

    @Column(name = "altitude")
    private String altitude;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "isdistrict")
    private Integer district;

    @Column(name = "isactive")
    private Integer active;

    @Column(name = "version")
    private Integer version;

    @Column(name = "settlementnameen")
    private String settlementnameen;

    @Column(name = "postalcode")
    private Integer postalcode;

}
