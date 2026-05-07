package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.11.2022
 * Time: 14:30
 */
@Embeddable
@Getter
@Setter
@Cacheable(false)
@EqualsAndHashCode
public class RudiTrainingCourseIndexIdEntity implements Serializable {

    @Column(name = "idx")
    private Integer index;

    @Column(name = "tce_id")
    private Integer trainingCourseId;
}
