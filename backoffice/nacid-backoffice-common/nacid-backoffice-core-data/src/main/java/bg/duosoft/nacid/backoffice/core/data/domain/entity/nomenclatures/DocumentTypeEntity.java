package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
@Table(name = "doc_types", schema = "nomenclatures")
public class DocumentTypeEntity implements IntegerKeyNomenclatureEntityBase {
    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(generator = "custom-sequence-generator")
//    @GenericGenerator(strategy = "bg.duosoft.nacid.backoffice.core.data.domain.CustomSequenceGenerator", name = "custom-sequence-generator", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "nomenclatures.doc_types_id_seq")})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "direction", nullable = false)
    private String direction;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "validation_file_group")
    private String validationFileGroup;

    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CfgDocTypeToDocCategoryEntity> details;


    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CfgDocTypeToAppStatusEntity> statuses;


    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CfgDocTypeToAbdocsConfigEntity> abdocsConfigs;
}
