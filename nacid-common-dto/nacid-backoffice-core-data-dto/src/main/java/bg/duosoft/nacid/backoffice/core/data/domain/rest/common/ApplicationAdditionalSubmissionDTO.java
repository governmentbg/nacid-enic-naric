package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAdditionalSubmissionDTO implements Serializable {
    private Integer id;
    private Integer applicationId;
    private String entryNum;
    private LocalDate entryDate;
    private String description;
    private LocalDateTime submissionDate;
    private String boUserAccepted;
    private LocalDateTime boDateTransferred;
    private List<ApplicationAdditionalAttachedDocDTO> attachedDocs;
}
