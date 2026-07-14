package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.09.2022
 * Time: 13:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "graduation_document_type", schema = "nomenclatures")
public class GraduationDocumentTypeEntity implements IntegerKeyNomenclatureEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @OneToMany(mappedBy = "graduationDocumentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CfgGraduationDocumentTypeConfigEntity> configs;
}
