package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationResponsibleUserDataDTO {
    private String responsibleUserName;
    private String fullName;
}
