package bg.duosoft.nacidservicesbe.domain.entity.lib;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 17:14
 */
@Entity
@Table(name = "lib_public_access", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class PublicAccessEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
