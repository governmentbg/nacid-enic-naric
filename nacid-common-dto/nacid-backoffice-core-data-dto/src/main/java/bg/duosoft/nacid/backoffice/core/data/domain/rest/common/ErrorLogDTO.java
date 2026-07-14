package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorLogDTO {
    private Integer id;
    private String errorType;
    private String errorMessage;
    private LocalDateTime createdDate;
    private LocalDateTime resolvedDate;
    private String resolvedComment;
    private String resolvedUser;
    private String dataJson;
    private String referenceId;

    public ErrorLogDTO(ErrorLogType errorType, String errorMessage) {
        this.errorType = errorType.code();
        this.errorMessage = errorMessage;
    }

    public ErrorLogDTO(ErrorLogType errorType, String errorMessage, String dataJson) {
        this.errorType = errorType.code();
        this.errorMessage = errorMessage;
        this.dataJson = dataJson;
    }

    public ErrorLogDTO(ErrorLogType errorType, String errorMessage, String dataJson, String referenceId) {
        this.errorType = errorType.code();
        this.errorMessage = errorMessage;
        this.dataJson = dataJson;
        this.referenceId = referenceId;
    }

}
