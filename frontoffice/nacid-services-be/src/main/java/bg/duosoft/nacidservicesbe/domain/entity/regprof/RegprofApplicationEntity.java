package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 18:03
 */
@Entity
@Table(name = "regprof_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofApplicationEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
