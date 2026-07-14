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
@Table(name = "commission_applications", schema = "rudi")
public class CommissionApplicationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "apn_id")
    private Integer applicationId;

    @Column(name = "calendar_id")
    private Integer calendarId;
    @Column(name = "motives")
    private String motives;

    @Column(name = "applicant_info")
    private String applicantInfo;

    @ManyToOne
    @JoinColumn(name = "attached_doc_id", referencedColumnName = "id")
    private ApplicationAttachedDocEntity attachedDoc;

}
