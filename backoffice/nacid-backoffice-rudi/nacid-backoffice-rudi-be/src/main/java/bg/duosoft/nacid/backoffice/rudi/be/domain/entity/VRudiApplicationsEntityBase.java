package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class VRudiApplicationsEntityBase {
    @Column(name = "ate_code")
    private String ateCode;

    @Column(name = "ase_code")
    private String aseCode;

    @Column(name = "ase_name")
    private String aseName;

    @Column(name = "entry_num")
    private String entryNum;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "efiling_id")
    private String efilingId;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "applicant_civil_id")
    private String applicantCivilId;

    @Column(name = "diploma_owner_name")
    private String diplomaOwnerName;

    @Column(name = "diploma_owner_civil_id")
    private String diplomaOwnerCivilId;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_country_name")
    private String universityCountryName;

    @Column(name = "university_country_code")
    private String universityCountryCode;

    @Column(name = "university_id")
    private Integer universityId;

    @Column(name = "responsible_user_name")
    private String responsibleUserName;

    @Column(name = "speciality_name")
    private String specialityName;

    @Column(name = "apn_status_name")
    private String apnStatusName;

    @Column(name = "apn_status_code")
    private String apnStatusCode;

    @Column(name = "docflow_status_code")
    private String docflowStatusCode;

    @Column(name = "docflow_status_name")
    private String docflowStatusName;

    @Column(name = "commission_sessions")
    private String commissionSessions;

    @Column(name = "experts_count")
    private Integer expertsCount;

    @Column(name = "experts_processed_status")
    private Integer expertsProcessedStatus;

    @Column(name = "recognized_prof_group_name")
    private String recognizedProfGroupName;

    @Column(name = "recognized_qualification")
    private String recognizedQualification;

    @Column(name = "recognized_edu_level_name")
    private String recognizedEduLevelName;

    @Column(name = "recognized_speciality_name")
    private String recognizedSpecialityName;

    @Column(name = "original_edu_level_name")
    private String eduLevelName;

    @Column(name = "original_edu_level_translated")
    private String originalEduLevelTranslated;

    @Column(name = "sar_flag")
    private String sarFlag;

    @Column(name = "personal_document_type_name")
    private String personalDocumentTypeName;

    @Column(name = "service_type_id")
    private String serviceTypeId;

    @Column(name = "crf_code")
    private String crfCode;

    @Column(name = "manual_temp_uni_name")
    private String manualTempUniName;
}
