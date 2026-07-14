package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AttachmentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "commission_calendar", schema = "rudi")
public class CommissionCalendarEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "session_num")
    private Integer sessionNum;

    @Column(name = "session_time")
    private LocalDateTime sessionTime;

    @Column(name = "notes")
    private String notes;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "secretary")
    private String secretary;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'COMMISSION_SESSION_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "session_status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "calendar_id", insertable = false, updatable = false)
    private List<CommissionApplicationEntity> applications;

    @OrderBy(value = "id ASC")
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommissionParticipationEntity> participations;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "commission_protocol_id", referencedColumnName = "id")
    private AttachmentEntity commissionProtocol;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scanned_commission_protocol_id", referencedColumnName = "id")
    private AttachmentEntity scannedCommissionProtocol;
}
