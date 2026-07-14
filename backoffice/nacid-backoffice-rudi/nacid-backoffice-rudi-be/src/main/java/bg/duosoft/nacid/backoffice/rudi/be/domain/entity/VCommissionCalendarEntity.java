package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vw_calendars", schema = "rudi")
@Cacheable(value = false)
public class VCommissionCalendarEntity implements Serializable {
    @Id
    private Integer id;

    @Column(name = "session_num")
    private Integer sessionNum;

    @Column(name = "session_time")
    private LocalDateTime sessionTime;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "status_code")
    private String statusCode;
}
