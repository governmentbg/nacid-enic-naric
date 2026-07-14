package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "application_commission_member_statements", schema = "rudi")
public class ApplicationCommissionMemberStatementEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    private RudiApplicationEntity application;

    @ManyToOne
    @JoinColumn(name = "commission_member_id", referencedColumnName = "id", nullable = false)
    private CommissionMemberEntity commissionMember;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "application_attached_doc_id", referencedColumnName = "id")
    private ApplicationAttachedDocEntity attachedDoc;

}
