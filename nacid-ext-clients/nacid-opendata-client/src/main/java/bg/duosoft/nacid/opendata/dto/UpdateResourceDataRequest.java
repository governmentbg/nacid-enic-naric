package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 17:25
 */
@Data
public class UpdateResourceDataRequest extends OpenDataRequest {
    @JsonProperty("resource_uri")
    private String resourceUri;

    @JsonProperty("extension_format")
    private String extensionFormat;

    private List<List<String>> data;
}
