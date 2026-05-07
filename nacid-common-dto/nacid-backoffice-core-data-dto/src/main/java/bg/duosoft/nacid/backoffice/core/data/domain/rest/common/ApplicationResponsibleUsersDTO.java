package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationResponsibleUsersDTO {
    private Integer id;
    private String responsibleUser;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String fullName;

    public boolean isLast() {
        return Objects.isNull(dateTo);
    }
}
