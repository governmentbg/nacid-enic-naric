package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 17:01
 */
@Data
public class EditResourceMetadataRequest extends OpenDataRequest {
    @Data
    public static class EditResourceMetadataData {
        private String name;
        private Integer type;
        private String locale;

    }
    @JsonProperty("resource_uri")
    private String resourceUri;
    private EditResourceMetadataData data;
}
