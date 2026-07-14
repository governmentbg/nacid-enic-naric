package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 12:05
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Cacheable(false)
public class RegprofTrainingCourseSpecialitiesIdEntity implements Serializable {

    @Column(name = "idx")
    private Integer idx;

    @Column(name = "rte_id")
    private Integer regprofTrainingCourseId;
}
