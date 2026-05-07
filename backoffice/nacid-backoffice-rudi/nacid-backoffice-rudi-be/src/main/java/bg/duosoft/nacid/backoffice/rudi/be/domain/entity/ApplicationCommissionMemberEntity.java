package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CommissionMemberPositionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 15:12
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "application_commission_members", schema = "rudi")
public class ApplicationCommissionMemberEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    private RudiApplicationEntity application;

    @ManyToOne
    @JoinColumn(name = "commission_member_id", referencedColumnName = "id", nullable = false)
    private CommissionMemberEntity commissionMember;

    @Column(name = "notes")
    private String notes;

    @Column(name = "course_content")
    private String courseContent;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'EDUCATION_LEVEL'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "edu_level", referencedColumnName="code"))
    })
    private ReferenceDataEntity eduLevel;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "previous_board_decisions")
    private String previousBoardDecisions;

    @Column(name = "similar_bulgarian_programs")
    private String similarBulgarianPrograms;

    @ManyToOne
    @JoinColumn(name = "member_position_code", referencedColumnName = "code")
    private CommissionMemberPositionEntity commissionMemberPosition;

    @ManyToOne
    @JoinColumn(name = "legal_reason_id", referencedColumnName = "id")
    private LegalReasonEntity legalReason;

    @OneToMany(mappedBy = "applicationCommissionMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationCommissionMemberSpecialityEntity> applicationCommissionMemberSpecialities;

    @Column(name = "process_status")
    private Integer processStatus;
}
