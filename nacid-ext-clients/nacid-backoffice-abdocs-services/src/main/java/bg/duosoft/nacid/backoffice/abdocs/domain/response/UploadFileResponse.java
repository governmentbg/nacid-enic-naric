package bg.duosoft.nacid.backoffice.abdocs.domain.response;

import bg.duosoft.nacid.backoffice.abdocs.domain.DocFileVisibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadFileResponse {
    private String docId;
    private String key;
    private String name;
    private String description;
    private String mimeType;
    private Integer dbId;
    private boolean isPrimary;
    private DocFileVisibility docFileVisibility;
}
