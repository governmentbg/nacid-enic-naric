package bg.duosoft.nacid.backoffice.abdocs.domain.response;

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
public class StoreFileResponse {
    private String fileKey;
    private String fileName;
    private String hash;
    private Integer size;
    private Integer dbId;
}
