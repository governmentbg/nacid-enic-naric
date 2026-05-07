package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonConnectedAppsCountDTO {
    private String personId;
    private Integer connectedApplicationsCount;
}
