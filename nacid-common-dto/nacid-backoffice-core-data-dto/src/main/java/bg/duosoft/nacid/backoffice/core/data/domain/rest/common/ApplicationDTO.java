package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ApplicationDTO implements Serializable {
    private Integer id;
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private String entryNumber;
    private LocalDate entryDate;
    private LocalDateTime dateCreated;
    private Boolean dataAuthenticFlag;
    private Boolean personalDataUsageFlag;
    private Boolean diffDiplomaNamesFlag;
    private Boolean officialEmailCommunicationFlag;
    private Boolean efilingSignedFlag;
    private String userCreated;
    private Integer efilingId;
    private String externalSystemId;
    private LocalDateTime externalSystemDate;
    private Integer rowVersion;
    private ReferenceDataDTO status;
    private ReferenceDataDTO docflowStatus;
    private PersonDTO applicant;
    private String representativeCapacity;
    private PersonDTO representative;
    private PersonDTO representativeCompany;
    private AddressDTO contactAddress;
    private ApplicantDiplomaNamesDTO applicantDiplomaNames;
    private String archiveNumber;
    private ReferenceDataDTO serviceType;
    private ReferenceDataDTO personalDocumentType;
    private List<ApplicationResponsibleUsersDTO> responsibleUsers;
    private List<AttachedDocDTO> attachments;
    private List<ApplicationCertificatesDTO> certificates;
    private List<ApplicationNotesDTO> applicationNotes;
    private List<ApplicationAdditionalSubmissionDTO> applicationAdditionalSubmissions;
    private List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods;
    private List<ApplicationDocumentReceiveOptionDTO> documentReceiveOptions;
    private ApplicationStatusHistoryDTO finalStatusHistory;
    private Boolean paidFlag;

    public List<AttachedDocDTO> getAttachmentsByDocTypes(Integer... docTypes) {
        if (docTypes == null || docTypes.length == 0) {
            return attachments;
        }
        Set<Integer> doctypesset = new HashSet<>();
        doctypesset.addAll(Arrays.stream(docTypes).toList());
        return attachments
                .stream()
                .filter(a -> doctypesset.contains(a.getDocumentType().getId()))
                .collect(Collectors.toList());
    }
    public List<ApplicationCertificatesDTO> getCertificatesByCertificateStatus(String... certificateStatus) {
        if (certificateStatus == null) {
            return getCertificates();
        }
        Set<String> csmap = Arrays.stream(certificateStatus).collect(Collectors.toSet());
        List<ApplicationCertificatesDTO> res = getCertificates();
        return res == null ? null : res.stream().filter(r -> csmap.contains(r.getCertificateStatus())).collect(Collectors.toList());
    }

}
