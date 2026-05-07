package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.Data;

@Data
public class ApplicationByPersonTableViewDTO extends ApplicationTableViewDTO {
    private String personRole;
}
