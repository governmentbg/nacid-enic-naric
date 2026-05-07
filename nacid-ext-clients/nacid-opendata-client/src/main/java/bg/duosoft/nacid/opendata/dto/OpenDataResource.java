package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 14:33
 */
@Data
public class OpenDataResource {
    private int id;
    private String uri;
    @JsonProperty("dataset_uri")
    private String datasetUri;
    private String name;
    private String description;
    private String locale;
    private String version;
    private String type;
    @JsonProperty("resource_url")
    private String resourceUrl;
    @JsonProperty("http_rq_type")
    private String httpRqType;
    private String authentication;
    @JsonProperty("custom_fields")
    private List<String> customFields;
    @JsonProperty("file_format")
    private String fileFormat;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Sofia")
    private Date cratedAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Sofia")
    private Date updatedAt;

    @JsonProperty("created_by")
    private Integer createdBy;
    @JsonProperty("updated_by")
    private Integer updatedBy;
    @JsonProperty("schema_description")
    private String schemaDescription;

    @JsonProperty("schema_url")
    private String schemaUrl;

    private Integer reported;
}
