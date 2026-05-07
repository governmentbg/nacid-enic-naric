package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "change_record_log", schema = "common")
public class ChangeRecordLogEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "record_id")
    private String recordId;

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "service")
    private String service;

    @Column(name = "operation")
    private String operation;

    @Column(name = "user_changed")
    private String userChanged;

    @Column(name = "date_changed")
    private LocalDateTime dateChanged;

    @Column(name = "before")
    private String beforeJson;

    @Column(name = "after")
    private String afterJson;

    @Column(name = "object_class")
    private String objectClass;

}
