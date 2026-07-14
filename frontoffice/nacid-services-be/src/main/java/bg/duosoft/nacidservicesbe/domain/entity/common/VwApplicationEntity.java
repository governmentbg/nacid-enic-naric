package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidservicesbe.domain.entity.lib.projection.BibliographicReferenceFullProjectionEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.projection.InquiryKindProjectionEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.projection.OfficialNoteDetailsProjectionEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiSarApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 09.01.2023
 * Time: 14:12
 */
@Entity
@Table(name = "vw_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class VwApplicationEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "ase_code")
    private String applicationSubtypeCode;

    @Column(name = "entry_num")
    private String entryNumber;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_submission_date")
    private LocalDateTime lastSubmissionDate;

    @Column(name = "temp_number")
    private String tempNumber;

    @Column(name = "fo_status_code")
    private String foStatusCode;

    @Column(name = "fo_status_name")
    private String foStatusName;

    @Column(name = "last_status_name")
    private String lastStatusName;

    @Column(name = "access_code")
    private String accessCode;

    @Column(name = "signed_flag")
    private Integer signedFlag;

    @Column(name = "paid_flag")
    private Integer paidFlag;

    @Column(name = "reverted_flag")
    private Integer revertedFlag;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "external_system_id")
    private String externalSystemId;

    @Column(name = "notes_count")
    private Integer notesCount;

    @Column(name = "service_type_id")
    private String serviceTypeId;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "apn_id")
    private RudiSarApplicationEntity sarApplication;

    @OneToMany
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private List<InquiryKindProjectionEntity> inquiryKinds;

    @OneToMany
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private List<OfficialNoteDetailsProjectionEntity> officialNotesDetails;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "apn_id")
    private BibliographicReferenceFullProjectionEntity biblioReference;

}
