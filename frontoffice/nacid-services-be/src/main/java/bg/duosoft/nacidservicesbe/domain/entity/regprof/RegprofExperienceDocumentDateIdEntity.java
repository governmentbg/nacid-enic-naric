package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import lombok.*;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.11.2022
 * Time: 14:41
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Cacheable(false)
public class RegprofExperienceDocumentDateIdEntity implements Serializable {

    @Column(name = "idx")
    private Integer dateIndex;

    @Embedded
    private RegprofExperienceDocumentIdEntity experienceDocumentId;

}
