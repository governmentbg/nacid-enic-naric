package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.*;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 25.08.2022
 * Time: 13:49
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Cacheable(value = false)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "application", schema = "common")
public class ApplicationEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName = "code")
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName = "code")
    private ApplicationSubtypeEntity applicationSubtype;

    @Column(name = "entry_num")
    private String entryNumber;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "data_authentic_flag", nullable = false)
    private Integer dataAuthenticFlag;

    @Column(name = "personal_data_usage_flag", nullable = false)
    private Integer personalDataUsageFlag;

    @Column(name = "diff_diploma_names_flag", nullable = false)
    private Integer diffDiplomaNamesFlag;

    @Column(name = "official_email_communication_flag", nullable = false)
    private Integer officialEmailCommunicationFlag;

    @Column(name = "efiling_signed_flag")
    private Integer efilingSignedFlag;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "efiling_id")
    private Integer efilingId;

    @Column(name = "external_system_id")
    private String externalSystemId;

    @Column(name = "external_system_date")
    private LocalDateTime externalSystemDate;

    @Column(name = "row_version")
    private Integer rowVersion;

    @Column(name = "paid_flag")
    private Integer paidFlag;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'APPLICATION_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity status;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'DOCFLOW_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "docflow_status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity docflowStatus;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private PersonEntity applicant;

    @Column(name = "representative_capacity")
    private String representativeCapacity;

    @ManyToOne
    @JoinColumn(name = "representative_id", referencedColumnName = "id")
    private PersonEntity representative;

    @ManyToOne
    @JoinColumn(name = "representative_company_id", referencedColumnName = "id")
    private PersonEntity representativeCompany;

    @ManyToOne
    @JoinColumn(name = "contact_address_id", referencedColumnName = "id")
    private AddressEntity contactAddress;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "application")
    private ApplicantDiplomaNamesEntity applicantDiplomaNames;

    @Column(name = "archive_num")
    private String archiveNumber;

    @OneToOne
    @JoinColumn(name = "final_status_history_id", referencedColumnName = "id")
    private ApplicationStatusHistoryEntity finalStatusHistory;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'SERVICE_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "service_type", referencedColumnName = "code"))
    })
    private ReferenceDataEntity serviceType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'PERSONAL_DOCUMENT_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "personal_document_type_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity personalDocumentType;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationAdditionalSubmissionEntity> applicationAdditionalSubmissions;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationDocumentReceiveMethodEntity> documentReceiveMethods;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationDocumentReceiveOptionEntity> documentReceiveOptions;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationResponsibleUsersEntity> responsibleUsers;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "doc_category = 'AA'")
    private List<ApplicationAttachedDocEntity> attachments;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationCertificatesEntity> certificates;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id asc")
    private List<ApplicationNotesEntity> applicationNotes;

}
