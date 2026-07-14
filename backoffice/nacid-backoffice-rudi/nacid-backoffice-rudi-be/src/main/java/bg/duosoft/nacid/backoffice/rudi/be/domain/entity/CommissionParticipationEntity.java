package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "commission_participation", schema = "rudi")
public class CommissionParticipationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "commission_member_id", referencedColumnName = "id", nullable = false)
    private CommissionMemberEntity commissionMember;

    @ManyToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "id", nullable = false)
    private CommissionCalendarEntity calendar;

    @Column(name = "notified")
    private Integer notified;

    @Column(name = "participated")
    private Integer participated;

    @Column(name = "chairman")
    private Integer chairman;
}
