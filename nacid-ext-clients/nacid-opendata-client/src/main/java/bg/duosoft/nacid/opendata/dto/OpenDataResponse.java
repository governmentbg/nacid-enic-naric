package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 11:38
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataResponse {
    private boolean success;
    private Integer status;
    private OpenDataResponseError error;
}
