package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "work_calendar_holiday", schema = "nomenclatures")
public class WorkCalendarHolidayEntity implements Serializable {

    @Id
    @Column(name = "id")
    private LocalDate id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_last_update")
    private LocalDateTime dateLastUpdate;

    @Column(name = "user_last_update")
    private String userLastUpdate;

}