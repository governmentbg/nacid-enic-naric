package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 13:27
 */
@Entity
@Table(name = "rudi_sar_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiSarApplicationEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer rudiApplicationId;

    @Column(name = "statute_flag")
    private Integer statuteFlag;

    @Column(name = "authenticity_flag")
    private Integer authenticityFlag;

    @Column(name = "recommendation_flag")
    private Integer recommendationFlag;

    @Column(name = "outgoing_number")
    private String outgoingNumber;

    @Column(name = "internal_number")
    private String internalNumber;
}
