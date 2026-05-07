package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachedDocDTO {
    private Integer id;
    private String description;
    private DocumentTypeDTO documentType;
    private ReferenceDataDTO copyType;
    private String docflowId;
    private String registrationNumber;
    private LocalDate registrationDate;
    private List<AttachedDocAttachmentDTO> attachedDocAttachments;
    private ReferenceDataDTO docCategory;
    private String abdocsViewDocumentUrl;
    private List<ApplicationCertificatesDTO> applicationCertificates;
}
