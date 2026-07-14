package bg.duosoft.nacidservicesbe.domain.entity.common;

import lombok.*;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.11.2022
 * Time: 13:09
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@Cacheable(false)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationIdIndexIdEntity implements Serializable {

    @Column(name = "idx")
    private Integer index;

    @Column(name = "apn_id")
    private Integer applicationId;
}
