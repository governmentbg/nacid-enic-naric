package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalNacidIdDTO {
    private String value;
    private String userGenerated;
    private LocalDateTime dateGenerated;
}
