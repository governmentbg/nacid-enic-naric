package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Cacheable(value = false)
@Table(name = "app_status_history", schema = "common")
public class ApplicationStatusHistoryEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apn_id")
    private Integer applicationId;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'APPLICATION_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity status;

    @ManyToOne
    @JoinColumn(name = "legal_reason_id", referencedColumnName = "id")
    private LegalReasonEntity legalReason;

    @Column(name = "commission_calendar_id")
    private Integer commissionCalendarId;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "user_created")
    private String userCreated;

    public ApplicationStatusHistoryEntity(Integer id) {
        this.id = id;
    }
}
