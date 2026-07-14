package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "graduation_document_type", schema = "nomenclatures")
public class GraduationDocTypeEntity implements Serializable, NomenclatureEntityBase<Integer> {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Integer active;

    @OneToMany(mappedBy = "graduationDocumentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GraduationDocTypeConfigEntity> configs;
}
