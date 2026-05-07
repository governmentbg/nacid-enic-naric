package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "error_log", schema = "common")
@Cacheable(value = false)
public class ErrorLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "error_type")
    private String errorType;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "resolved_date")
    private LocalDateTime resolvedDate;

    @Column(name = "resolved_comment")
    private String resolvedComment;

    @Column(name = "resolved_user")
    private String resolvedUser;

    @Column(name = "data_json")
    private String dataJson;

    @Column(name = "reference_id")
    private String referenceId;

}
