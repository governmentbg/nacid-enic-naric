package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 25.08.2022
 * Time: 14:03
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "rudi_application", schema = "rudi")
@PrimaryKeyJoinColumn(name = "apn_id")
public class RudiApplicationEntity extends ApplicationEntity {

    @Column(name = "bg_address_owner", nullable = false)
    private String bgAddressOwner;

    @Column(name = "representative_authorized_flag")
    private Integer representativeAuthorizedFlag;

    @Column(name = "submitted_docs")
    private String submittedDocs;

    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "application")
    @PrimaryKeyJoinColumn
    private TrainingCourseEntity trainingCourse;

    @OrderBy(value = "id ASC")
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationCommissionMemberEntity> applicationCommissionMembers;

    @OrderBy(value = "id ASC")
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationCommissionMemberStatementEntity> applicationCommissionMemberStatements;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationRecognitionPurposeEntity> applicationRecognitionPurposes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", insertable = false, updatable = false)
    private List<CommissionApplicationEntity> commissionApplications;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "application")
    private List<ApplicationRecognizedSpecialityEntity> recognizedSpecialities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "application")
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", insertable = false, updatable = false)
    private ApplicationRecognizedDetailsEntity applicationRecognizedDetails;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "application")
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", insertable = false, updatable = false)
    private SarApplicationEntity sarApplication;

    @ManyToOne
    @JoinColumn(name = "legal_reason_id", referencedColumnName = "id")
    private LegalReasonEntity legalReason;
}
