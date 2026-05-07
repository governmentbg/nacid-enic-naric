package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 13.09.2022
 * Time: 13:26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cacheable(value = false)
@Table(name = "profession_experience_document_type", schema = "nomenclatures")
public class ProfessionExperienceDocumentTypeEntity implements Serializable, StringKeyNomenclatureEntityBase {
    public ProfessionExperienceDocumentTypeEntity(String id) {
        this.id = id;
    }
    @Id
    @Column(name = "code", nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;
    @Column(name = "for_experience_calculation_flag", nullable = false)
    private Integer forExperienceCalculationFlag;
}
