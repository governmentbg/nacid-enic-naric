package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vw_original_edu_level", schema = "nomenclatures")
@Cacheable(value = false)
public class VOriginalEduLevelEntity implements Serializable {
    @Id
    @Column(name = "original_edu_level_name")
    private String originalEduLevelName;
}
