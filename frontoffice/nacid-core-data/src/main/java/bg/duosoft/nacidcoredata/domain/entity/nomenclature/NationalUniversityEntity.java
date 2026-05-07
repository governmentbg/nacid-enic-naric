package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "national_university", schema = "nomenclatures")
public class NationalUniversityEntity implements Serializable, NomenclatureEntityBase<String> {

    @Id
    @Column(name = "eik", nullable = false, length = 20)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @ManyToOne
    @JoinColumn(name = "settlement_code", referencedColumnName = "code")
    private EkSettlementEntity settlement;

    @Column(name = "address")
    private String address;

    @Column(name = "address_en")
    private String addressEn;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "website")
    private String website;

    @Column(name = "logo_rel_path")
    private String logoRelativePath;

    @Column(name = "active")
    private Integer active;
}
