package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChangeRecordLogSimpleDTO {
    private Integer id;
    private String recordId;
    private String serviceName;
    private String operation;
    private LocalDateTime dateChanged;
    private String userChanged;
}
