package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 13:41
 */
@Entity
@Table(name = "rudi_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiApplicationEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
