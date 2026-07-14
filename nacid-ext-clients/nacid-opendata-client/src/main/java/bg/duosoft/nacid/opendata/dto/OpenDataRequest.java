package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 11:00
 */
@Data
public class OpenDataRequest {
    @JsonProperty("api_key")
    private String apiKey;
}
