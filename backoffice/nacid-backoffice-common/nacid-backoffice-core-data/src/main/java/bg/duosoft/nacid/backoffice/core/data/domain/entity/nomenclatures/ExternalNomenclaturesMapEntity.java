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
@Table(name = "external_nomenclatures_map", schema = "nomenclatures")
public class ExternalNomenclaturesMapEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "system", nullable = false, length = 50)
    private String system;

    @Column(name = "nomenclature_type", nullable = false, length = 50)
    private String nomenclatureType;

    @Column(name = "internal_nom_id", nullable = false, length = 20)
    private String internalNomId;

    @Column(name = "condition1", length = 50)
    private String condition1;

    @Column(name = "condition2", length = 50)
    private String condition2;

    @Column(name = "external_nom_id", nullable = false, length = 20)
    private String externalNomId;


}