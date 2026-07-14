package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "profession_experience_document_type", schema = "nomenclatures")
public class ProfExperienceDocTypeEntity implements Serializable, NomenclatureEntityBase<String> {

    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "for_experience_calculation_flag")
    private Integer forExperienceCalculation;

    @Column(name = "active")
    private Integer active;
}
