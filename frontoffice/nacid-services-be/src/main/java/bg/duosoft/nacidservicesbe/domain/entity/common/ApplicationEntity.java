package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ApplicationSubtypeEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 15:38
 */
@Entity
@Table(name = "application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @ManyToOne
    @JoinColumn(name = "ase_code")
    private ApplicationSubtypeEntity applicationSubtype;

    @Column(name = "entry_num")
    private String entryNumber;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id")
    private PersonEntity applicant;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "representative_id")
    private PersonEntity representative;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_address_id")
    private AddressEntity contactAddress;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "temp_number")
    private String tempNumber;

    @Column(name = "personal_data_usage_flag")
    private Integer personalDataUsageFlag;

    @Column(name = "data_authentic_flag")
    private Integer dataAuthenticFlag;

    @Column(name = "diff_diploma_names_flag")
    private Integer diffDiplomaNamesFlag;

    @Column(name = "access_code")
    private String accessCode;

    @Column(name = "representative_company_id")
    private String representativeCompanyId;

    @Column(name = "representative_capacity")
    private String representativeCapacity;

    @Column(name = "applicant_title_before")
    private String applicantTitleBefore;

    @Column(name = "applicant_title_after")
    private String applicantTitleAfter;

    @Column(name = "signed_flag")
    private Integer signedFlag;

    @Column(name = "paid_flag")
    private Integer paidFlag;

    @Column(name = "external_system_id")
    private String externalSystemId;

    @Column(name = "external_system_document_id")
    private String externalSystemDocumentId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "multiple_application_id")
    private MultipleApplicationEntity multipleApplication;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'SERVICE_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "service_type_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity serviceType;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "id", updatable = false)
    private List<ApplicationDocumentReceiveMethodEntity> applicationDocumentReceiveMethods;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true)
    private ApplicantDiplomaNamesEntity diffDiplomaNames;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "id", updatable = false)
    private List<ApplicationAttachedDocEntity> attachedDocs;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "id", updatable = false)
    @OrderBy("dateCreated DESC, active DESC")
    private List<ApplicationReceiptEntity> receipts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "id", updatable = false)
    @OrderBy("dateCreated DESC")
    private List<ApplicationNoteEntity> notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true)
    @OrderBy("dateCreated DESC")
    private List<AppStatusHistoryEntity> statusHistory;

    @Transient
    private boolean original = true;

    public String getFoStatusCode(){
        if(statusHistory != null) {
            List<AppStatusHistoryEntity> currentStatusList = getSortedHistoryStream().filter(h -> h.getFoStatus() != null).collect(Collectors.toList());
            if(currentStatusList.size() > 0) {
                return currentStatusList.get(currentStatusList.size() - 1).getFoStatus().getPk().getId();
            }
        }
        return null;
    }

    public String getLastStatusName(){
        if(statusHistory != null) {
            List<AppStatusHistoryEntity> currentStatusList = getSortedHistoryStream().collect(Collectors.toList());
            if(currentStatusList.size() > 0) {
                AppStatusHistoryEntity last = currentStatusList.get(currentStatusList.size() - 1);
                return last.getStatusName();
            }
        }
        return null;
    }


    public LocalDateTime getLastSubmissionDate(){
        if(statusHistory != null) {
            List<AppStatusHistoryEntity> lastSubList = getSortedHistoryStream().filter(
                    h -> h.getFoStatus() != null && (h.getFoStatus().getPk().getId().equals(FoApplicationStatus.SUBMITTED.getCode()) || h.getFoStatus().getPk().getId().equals(FoApplicationStatus.SUBMITTED_WITH_SIGNATURE.getCode()))
            ).collect(Collectors.toList());
            if(lastSubList.size() > 0) {
                return lastSubList.get(lastSubList.size() - 1).getDateCreated();
            }
        }
        return null;
    }

    public Stream<AppStatusHistoryEntity> getSortedHistoryStream(){
        Stream<AppStatusHistoryEntity> sortedStream = statusHistory.stream().sorted(Comparator.comparingInt(AppStatusHistoryEntity::getId));
        return sortedStream;
    }

}
