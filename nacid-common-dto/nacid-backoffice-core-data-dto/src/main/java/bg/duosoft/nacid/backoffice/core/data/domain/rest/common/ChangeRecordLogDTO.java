package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChangeRecordLogDTO {
    private Integer id;
    private String recordId;
    private String applicationName;
    private String service;
    private String operation;
    private String userChanged;
    private LocalDateTime dateChanged;
    private String beforeJson;
    private String afterJson;
    private String objectClass;
}
