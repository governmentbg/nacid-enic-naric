package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.*;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        PersonMapper.class,
        AddressMapper.class,
        ReferenceDataMapper.class,
        ApplicationTypeMapper.class,
        IntegerToBooleanMapper.class,
        ApplicantDiplomaNamesMapper.class,
        ApplicationSubtypeMapper.class,
        DocumentReceiveMethodMapper.class,
        ApplicationDocumentReceiveMethodMapper.class,
        ApplicationDocumentReceiveOptionMapper.class,
        ApplicationAdditionalSubmissionMapper.class,
        ApplicationAttachedDocMapper.class,
        ApplicationCertificatesMapper.class,
        ApplicationNotesMapper.class,
        ApplicationResponsibleUsersMapper.class,
        ApplicationCertificatesMapper.class,
        ApplicationStatusHistoryMapper.class,
})
public abstract class ApplicationMapper extends BaseObjectMapper<ApplicationEntity, ApplicationDTO> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "entryNumber", source = "entryNumber")
    @Mapping(target = "entryDate", source = "entryDate")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "dataAuthenticFlag", source = "dataAuthenticFlag")
    @Mapping(target = "personalDataUsageFlag", source = "personalDataUsageFlag")
    @Mapping(target = "diffDiplomaNamesFlag", source = "diffDiplomaNamesFlag")
    @Mapping(target = "userCreated", source = "userCreated")
    @Mapping(target = "efilingId", source = "efilingId")
    @Mapping(target = "externalSystemId", source = "externalSystemId")
    @Mapping(target = "externalSystemDate", source = "externalSystemDate")
    @Mapping(target = "rowVersion", source = "rowVersion")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "docflowStatus", source = "docflowStatus")
    @Mapping(target = "applicant", source = "applicant")
    @Mapping(target = "representativeCapacity", source = "representativeCapacity")
    @Mapping(target = "representative", source = "representative")
    @Mapping(target = "representativeCompany", source = "representativeCompany")
    @Mapping(target = "contactAddress", source = "contactAddress")
    @Mapping(target = "applicantDiplomaNames", source = "applicantDiplomaNames")
    @Mapping(target = "archiveNumber", source = "archiveNumber")
    @Mapping(target = "officialEmailCommunicationFlag", source = "officialEmailCommunicationFlag")
    @Mapping(target = "efilingSignedFlag", source = "efilingSignedFlag")
    @Mapping(target = "serviceType", source = "serviceType")
    @Mapping(target = "personalDocumentType", source = "personalDocumentType")
    @Mapping(target = "responsibleUsers", source = "responsibleUsers")
    @Mapping(target = "attachments", source = "attachments")
    @Mapping(target = "certificates", source = "certificates")
    @Mapping(target = "applicationNotes", source = "applicationNotes")
    @Mapping(target = "documentReceiveMethods", source = "documentReceiveMethods")
    @Mapping(target = "documentReceiveOptions", source = "documentReceiveOptions")
    @Mapping(target = "applicationAdditionalSubmissions", source = "applicationAdditionalSubmissions")
    @Mapping(target = "finalStatusHistory", source = "finalStatusHistory")
    @Mapping(target = "paidFlag", source = "paidFlag")
    public abstract ApplicationDTO toDto(ApplicationEntity entity);

    @InheritInverseConfiguration
    public abstract ApplicationEntity toEntity(ApplicationDTO dto);

    @AfterMapping
    public void afterToEntity(ApplicationDTO source, @MappingTarget ApplicationEntity target) {
        ApplicationSubtypeEntity applicationSubtype = target.getApplicationSubtype();
        if (Objects.nonNull(applicationSubtype)) {
            applicationSubtype.setApplicationType(target.getApplicationType());
        }

        setDefaultFlags(target);
        setMissingFields(target);
    }

    private static void setMissingFields(ApplicationEntity target) {
        ApplicantDiplomaNamesEntity applicantDiplomaNames = target.getApplicantDiplomaNames();
        if (Objects.nonNull(applicantDiplomaNames)) {
            applicantDiplomaNames.setId(target.getId());
            if (Objects.isNull(applicantDiplomaNames.getApplication())) {
                applicantDiplomaNames.setApplication(target);
            }
        }

        List<ApplicationAttachedDocEntity> attachments = target.getAttachments();
        if (!CollectionUtils.isEmpty(attachments)) {
            attachments.forEach(a -> a.setApplication(target));
        }

        List<ApplicationCertificatesEntity> certificates = target.getCertificates();
        if (!CollectionUtils.isEmpty(certificates)) {
            certificates.forEach(a -> a.setApplication(target));
        }

        List<ApplicationDocumentReceiveMethodEntity> documentReceiveMethods = target.getDocumentReceiveMethods();
        if (!CollectionUtils.isEmpty(documentReceiveMethods)) {
            documentReceiveMethods.forEach(a -> a.setApplication(target));
        }

        List<ApplicationDocumentReceiveOptionEntity> documentReceiveOptions = target.getDocumentReceiveOptions();
        if (!CollectionUtils.isEmpty(documentReceiveOptions)) {
            documentReceiveOptions.forEach(a -> a.setApplication(target));
        }

        List<ApplicationNotesEntity> applicationNotes = target.getApplicationNotes();
        if (!CollectionUtils.isEmpty(applicationNotes)) {
            applicationNotes.forEach(a -> {
                a.setApplication(target);

                Integer id = a.getId();
                if (Objects.isNull(id)) {
                    a.setCreatedUser(SecurityUtils.getUsername());
                    a.setCreatedDate(LocalDateTime.now());
                }
            });
        }

        List<ApplicationResponsibleUsersEntity> responsibleUsers = target.getResponsibleUsers();
        if (!CollectionUtils.isEmpty(responsibleUsers)) {
            responsibleUsers.forEach(r -> r.setApplication(target));
        }

        List<ApplicationAdditionalSubmissionEntity> applicationAdditionalSubmissions = target.getApplicationAdditionalSubmissions();
        if (!CollectionUtils.isEmpty(applicationAdditionalSubmissions)) {
            applicationAdditionalSubmissions.forEach(a -> a.setApplication(target));
        }
    }

    private static void setDefaultFlags(ApplicationEntity target) {
        Integer officialEmailCommunicationFlag = target.getOfficialEmailCommunicationFlag();
        if (Objects.isNull(officialEmailCommunicationFlag)) {
            target.setOfficialEmailCommunicationFlag(0);
        }

        Integer dataAuthenticFlag = target.getDataAuthenticFlag();
        if (Objects.isNull(dataAuthenticFlag)) {
            target.setDataAuthenticFlag(0);
        }

        Integer personalDataUsageFlag = target.getPersonalDataUsageFlag();
        if (Objects.isNull(personalDataUsageFlag)) {
            target.setPersonalDataUsageFlag(0);
        }

        Integer diffDiplomaNamesFlag = target.getDiffDiplomaNamesFlag();
        if (Objects.isNull(diffDiplomaNamesFlag)) {
            target.setDiffDiplomaNamesFlag(Objects.isNull(target.getApplicantDiplomaNames()) ? 0 : 1);
        }
    }

}
